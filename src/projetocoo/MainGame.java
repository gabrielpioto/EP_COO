package projetocoo;

/* oi*/
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import projetocoo.model.Background;
import projetocoo.model.Enemy1;
import projetocoo.model.Enemy2;
import projetocoo.model.Enemy3;
import projetocoo.model.Player;
import projetocoo.model.PowerUp1;
import projetocoo.model.PowerUp2;
import projetocoo.model.base.Drawable;
import projetocoo.model.base.Enemy;
import projetocoo.model.base.PowerUp;
import projetocoo.model.base.Updatable;
import projetocoo.model.shooter.ActiveShooter;

public class MainGame {

	private static MainGame mainGame = new MainGame();
	private static final int NUMBER_ENEMIES_1 = 10;
	private static final int NUMBER_ENEMIES_2 = 10;
	private static final int NUMBER_BACKS_1 = 20;
	private static final int NUMBER_BACKS_2 = 50;
	private static final int NUMBER_POWERUPS_1 = 1;
	private static final int NUMBER_POWERUPS_2 = 1;

	/* Indica que o jogo está em execução */
	boolean running = true;

	/* variáveis usadas no controle de tempo efetuado no main loop */

	private long delta;
	private long currentTime = System.currentTimeMillis();

	private long nextEnemy1Delay = (currentTime + 2000);
	private long nextEnemy2Delay = (currentTime + 7000);
	private long nextEnemy3Delay = (currentTime + 5000);

	private long nextPowerUp1Delay = (currentTime + 1000);
	private long nextPowerUp2Delay = (currentTime + 1000);

	private Player player = new Player(GameLib.WIDTH / 2,
			GameLib.HEIGHT * 0.90, 0.25, 0.25, 12, currentTime);

	private List<Enemy1> enemies1 = new ArrayList<Enemy1>(NUMBER_ENEMIES_1);

	private List<Enemy2> enemies2 = new ArrayList<Enemy2>(NUMBER_ENEMIES_2);

	private List<Enemy3> enemies3 = Collections.singletonList(new Enemy3());

	private List<Background> backs1 = new ArrayList<Background>(NUMBER_BACKS_1);

	private List<Background> backs2 = new ArrayList<Background>(NUMBER_BACKS_2);

	private List<PowerUp1> powerUps1 = new ArrayList<PowerUp1>(
			NUMBER_POWERUPS_1);

	private List<PowerUp2> powerUps2 = new ArrayList<PowerUp2>(
			NUMBER_POWERUPS_2);

	public MainGame() {

		this.player.setState(new ActiveShooter());

		this.currentTime = System.currentTimeMillis();

		for (int i = 0; i < NUMBER_ENEMIES_1; i++) {
			enemies1.add(new Enemy1(9.0));
		}

		for (int i = 0; i < NUMBER_ENEMIES_2; i++) {
			enemies2.add(new Enemy2(12.0));
		}

		for (int i = 0; i < NUMBER_POWERUPS_1; i++) {
			powerUps1.add(new PowerUp1(8.0));
		}

		for (int i = 0; i < NUMBER_POWERUPS_2; i++) {
			powerUps2.add(new PowerUp2(6.0));
		}

		for (int i = 0; i < NUMBER_BACKS_1; i++) {
			double x = Math.random() * GameLib.WIDTH;
			double y = Math.random() * GameLib.HEIGHT;
			backs1.add(new Background(x, y, 0.07, 3, Color.GRAY));
		}

		for (int i = 0; i < NUMBER_BACKS_2; i++) {
			double x = Math.random() * GameLib.WIDTH;
			double y = Math.random() * GameLib.HEIGHT;
			backs2.add(new Background(x, y, 0.045, 2, Color.DARK_GRAY));
		}


	}

	public void run() {

		/* iniciado interface gráfica */
		GameLib.initGraphics();

		/*************************************************************************************************/
		/*                                                                                               */
		/* Main loop do jogo                                                                             */
		/*                                                                                               */
		/* O main loop do jogo possui executa as seguintes operações:                                    */
		/*                                                                                               */
		/* 1) Verifica se há colisões e atualiza estados dos elementos conforme a necessidade.           */
		/*                                                                                               */
		/* 2) Atualiza estados dos elementos baseados no tempo que correu desde a última atualização     */
		/*    e no timestamp atual: posição e orientação, execução de disparos de projéteis, etc.        */
		/*                                                                                               */
		/* 3) Processa entrada do usuário (teclado) e atualiza estados do player conforme a necessidade. */
		/*                                                                                               */
		/* 4) Desenha a cena, a partir dos estados dos elementos.                                        */
		/*                                                                                               */
		/* 5) Espera um período de tempo (de modo que delta seja aproximadamente sempre constante).      */
		/*                                                                                               */
		/*************************************************************************************************/
		
		while (running) {
			//
			// /* Usada para atualizar o estado dos elementos do jogo */
			// /* (player, projéteis e inimigos) "delta" indica quantos */
			// /* ms se passaram desde a última atualização. */
			//
			delta = System.currentTimeMillis() - currentTime;
			//
			// /* Já a variável "currentTime" nos dá o timestamp atual. */
			//
			this.currentTime = System.currentTimeMillis();
			//
			// /***************************/
			// /* Verificação de colisões */
			// /***************************/

			for (Enemy enemy : enemies1) {
				player.collide(enemy.getProjectiles());
				enemy.collide(player.getProjectiles());
			}

			for (Enemy enemy : enemies2) {
				player.collide(enemy.getProjectiles());
				enemy.collide(player.getProjectiles());
			}

			for (Enemy enemy : enemies3) {
				enemy.collide(Collections.singletonList(player));
			}

			player.collide(enemies1);
			player.collide(enemies2);
			player.collide(enemies3);

			/***************************/
			/* Atualizações de estados */
			/***************************/

			/* player */
			player.update();

			/* projeteis (player) */
			update(player.getProjectiles());

			/* inimigos */
			updateEnemies(enemies1);
			updateEnemies(enemies2);
			updateEnemies(enemies3);

			/* powerUps */
			update(powerUps1);
			update(powerUps2);

			/* Spawns */
			Enemy.Spawn(enemies1, nextEnemy1Delay);
			Enemy.Spawn(enemies2, nextEnemy2Delay);
			Enemy.Spawn(enemies3, nextEnemy3Delay);
			PowerUp.Spawn(powerUps1, nextPowerUp1Delay);
			PowerUp.Spawn(powerUps2, nextPowerUp2Delay);

			/********************************************/
			/* Verificando entrada do usuário (teclado) */
			/********************************************/
			player.updatePosition();

			/*******************/
			/* Desenho da cena */
			/*******************/

			/* desenhando plano fundo */

			draw(backs1);
			draw(backs2);

			/* desenhando player */

			player.draw();

			/* desenhando projeteis (player) */

			draw(player.getProjectiles());

			/* desenhando inimigos */

			drawEnemies(enemies1);
			drawEnemies(enemies2);
			drawEnemies(enemies3);

			/* desenhando powerups */

			draw(powerUps1);
			draw(powerUps2);

			/*
			 * chamama a display() da classe GameLib atualiza o desenho exibido
			 * pela interface do jogo.
			 */

			GameLib.display();

			/*
			 * faz uma pausa de modo que cada execução do laço do main loop
			 * demore aproximadamente 5 ms.
			 */

			busyWait(currentTime + 5);

		}

	}

	private void updateEnemies(Collection<? extends Enemy> c) {
		for (Enemy e : c) {
			update(e.getProjectiles());
			e.update();
		}
	}

	private void update(Collection<? extends Updatable> c) {
		for (Updatable e : c)
			e.update();
	}

	private void drawEnemies(Collection<? extends Enemy> c) {
		for (Enemy e : c) {
			draw(e.getProjectiles());
			e.draw();
		}
	}

	private void draw(Collection<? extends Drawable> c) {
		for (Drawable e : c)
			e.draw();
	}

	public void setNextPowerUp1Delay(long nextPowerUp1Delay) {
		this.nextPowerUp1Delay = nextPowerUp1Delay;
	}

	public void setNextPowerUp2Delay(long nextPowerUp2Delay) {
		this.nextPowerUp2Delay = nextPowerUp2Delay;
	}

	public void setNextEnemy1Delay(long nextEnemy1Delay) {
		this.nextEnemy1Delay = nextEnemy1Delay;
	}

	public void setNextEnemy2Delay(long nextEnemy2Delay) {
		this.nextEnemy2Delay = nextEnemy2Delay;
	}

	public void setNextEnemy3Delay(long nextEnemy3Delay) {
		this.nextEnemy3Delay = nextEnemy3Delay;
	}

	public List<Enemy2> getEnemies2() {
		return enemies2;
	}

	public long getDelta() {
		return delta;
	}

	public long getCurrentTime() {
		return currentTime;
	}

	public static MainGame getInstance() {
		return mainGame;
	}

	public Player getPlayer() {
		return player;
	}

	public static void busyWait(long time) {
		while (System.currentTimeMillis() < time)
			Thread.yield();
	}

	public void pauseResume() {
		running = !running;
	}
	
	/** método main (para a execução) **/
	public static void main(String[] args){
		MainGame.getInstance().run();
	}

}
