package projetocoo;

/* oi*/
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import projetocoo.model.Background;
import projetocoo.model.Enemy1;
import projetocoo.model.Enemy2;
import projetocoo.model.Enemy3;
import projetocoo.model.Player;
import projetocoo.model.PowerUp1;
import projetocoo.model.base.Element;
import projetocoo.model.base.Enemy;
import projetocoo.model.base.PowerUp;
import projetocoo.model.base.Projectile;
import projetocoo.model.shooter.ActiveShooter;

public class MainGame {

	private static MainGame mainGame = new MainGame();
	private static final int NUMBER_ENEMIES_1 = 10;
	private static final int NUMBER_ENEMIES_2 = 10;
	private static final int NUMBER_BACKS_1 = 20;
	private static final int NUMBER_BACKS_2 = 50;
	private static final int NUMBER_POWERUPS_1 = 1;

	/* Indica que o jogo está em execução */
	boolean running = true;

	/* variáveis usadas no controle de tempo efetuado no main loop */

	private long delta;
	private long currentTime = System.currentTimeMillis();
	private long nextEnemy1Delay = (currentTime + 2000);
	private long nextEnemy2Delay = (currentTime + 7000);
	private long nextEnemy3Delay = (currentTime + 5000);
	private long nextPowerUp1Delay = (currentTime + 1000);

	public void setNextPowerUp1Delay(long nextPowerUp1Delay) {
		this.nextPowerUp1Delay = nextPowerUp1Delay;
	}

	public long getNextPowerUp1Delay() {
		return nextPowerUp1Delay;
	}

	public long getNextEnemy1Delay() {
		return nextEnemy1Delay;
	}

	public void setNextEnemy1Delay(long nextEnemy1Delay) {
		this.nextEnemy1Delay = nextEnemy1Delay;
	}

	public long getNextEnemy2Delay() {
		return nextEnemy2Delay;
	}

	public void setNextEnemy2Delay(long nextEnemy2Delay) {
		this.nextEnemy2Delay = nextEnemy2Delay;
	}

	public long getNextEnemy3Delay() {
		return nextEnemy3Delay;
	}

	public void setNextEnemy3Delay(long nextEnemy3Delay) {
		this.nextEnemy3Delay = nextEnemy3Delay;
	}

	private Player player = new Player(GameLib.WIDTH / 2,
			GameLib.HEIGHT * 0.90, 0.25, 0.25, 12, currentTime);

	// Enemy1 enemy1 = new Enemy1(9.0, 2000);
	// Enemy2 enemy2 = new Enemy2(12.0, 7000, GameLib.WIDTH * 0.20, 0);
	private List<Enemy1> enemies1 = new ArrayList<Enemy1>(NUMBER_ENEMIES_1);

	private List<Enemy2> enemies2 = new ArrayList<Enemy2>(NUMBER_ENEMIES_2);

	private List<Enemy3> enemies3 = Collections.singletonList(new Enemy3());

	private List<Background> backs1 = new ArrayList<Background>(NUMBER_BACKS_1);

	private List<Background> backs2 = new ArrayList<Background>(NUMBER_BACKS_2);

	private List<PowerUp1> powerUps1 = new ArrayList<PowerUp1>(
			NUMBER_POWERUPS_1);

	// private List<Projectile> playerProjectiles= new
	// ArrayList<Projectile>(NUMBER_PROJECTILES);

	// private List<Projectile> enemyProjectiles= new
	// ArrayList<Projectile>(NUMBER_e_PROJECTILES);

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
			powerUps1.add(new PowerUp1(5.0));
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

		/*
		 * for (int i = 0; i < NUMBER_PROJECTILES; i++){
		 * playerProjectiles.add(new Projectile(0, 0, 0, 0, Color.GREEN)); }
		 */

		/*
		 * for (int i = 0; i < NUMBER_e_PROJECTILES; i++){
		 * enemyProjectiles.add(new Projectile(0, 0, 0, 0, Color.RED)); }
		 */

		// TODO checar os parametros que envio para criar um inimigo
		// for (int i = 0; i < NUMBER_ENEMIES_1; i++) {
		// enemies1.add(new Enemy1(9.0));
		// }
		//
		// for (int i = 0; i < NUMBER_ENEMIES_2; i++) {
		// enemies2.add(new Enemy2(0));
		// }
		//
		// System.exit(0);
		//
	}

	public void run() {

		/* iniciado interface gráfica */
		GameLib.initGraphics();

		//
		// /*************************************************************************************************/
		// /* */
		// /* Main loop do jogo */
		// /* */
		// /* O main loop do jogo possui executa as seguintes operações: */
		// /* */
		// /* 1) Verifica se há colisões e atualiza estados dos elementos
		// conforme a necessidade. */
		// /* */
		// /* 2) Atualiza estados dos elementos baseados no tempo que correu
		// desde a última atualização */
		// /* e no timestamp atual: posição e orientação, execução de
		// disparos de projéteis, etc. */
		// /* */
		// /* 3) Processa entrada do usuário (teclado) e atualiza estados do
		// player conforme a necessidade. */
		// /* */
		// /* 4) Desenha a cena, a partir dos estados dos elementos. */
		// /* */
		// /* 5) Espera um período de tempo (de modo que delta seja
		// aproximadamente sempre constante). */
		// /* */
		// /*************************************************************************************************/
		//
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
			
			for(Enemy enemy : enemies3){
				enemy.collide(Collections.singletonList(player));
			}

			player.collide(enemies1);
			player.collide(enemies2);
			player.collide(enemies3);

			//
			// /***************************/
			// /* Atualizações de estados */
			// /***************************/
			//
			// /* projeteis (player) */
			player.update();
			player.updatePosition();

			for (Projectile p : player.getProjectiles()) {
				p.update();
			}

			// /* projeteis (inimigos) */
			// /* inimigos tipo 1 */
			for (Enemy e : enemies1) {
				for (Projectile p : e.getProjectiles()) {
					p.update();
				}
				e.update();
				// e.updatePosition();
			}

			// /* inimigos tipo 2 */
			for (Enemy e : enemies2) {
				for (Projectile p : e.getProjectiles()) {
					p.update();
				}
				e.update();
				// e.updatePosition();
			}

			for (Enemy3 e : enemies3) {
				e.update();
			}

			for (PowerUp1 p : powerUps1) {
				p.update();
			}

			Enemy.Spawn(enemies1, nextEnemy1Delay);
			Enemy.Spawn(enemies2, nextEnemy2Delay);
			Enemy.Spawn(enemies3, nextEnemy3Delay);
			PowerUp.Spawn(powerUps1, nextPowerUp1Delay);

			//
			// /********************************************/
			// /* Verificando entrada do usuário (teclado) */
			// /********************************************/
			//
			// if(player_state == ACTIVE){
			//
			// if(GameLib.iskeyPressed(GameLib.KEY_UP)) player_Y -= delta *
			// player_VY;
			// if(GameLib.iskeyPressed(GameLib.KEY_DOWN)) player_Y += delta *
			// player_VY;
			// if(GameLib.iskeyPressed(GameLib.KEY_LEFT)) player_X -= delta *
			// player_VX;
			// if(GameLib.iskeyPressed(GameLib.KEY_RIGHT)) player_X += delta *
			// player_VY;
			// if(GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {
			//
			// if(currentTime > player_nextShot){
			//
			// int free = findFreeIndex(projectile_states);
			//
			// if(free < projectile_states.length){
			//
			// projectile_X[free] = player_X;
			// projectile_Y[free] = player_Y - 2 * player_radius;
			// projectile_VX[free] = 0.0;
			// projectile_VY[free] = -1.0;
			// projectile_states[free] = 1;
			// player_nextShot = currentTime + 100;
			// }
			// }
			// }
			// }
			//
			// if(GameLib.iskeyPressed(GameLib.KEY_ESCAPE)) running = false;
			//
			// /* Verificando se coordenadas do player ainda estão dentro */
			// /* da tela de jogo após processar entrada do usuário. */
			//
			// if(player_X < 0.0) player_X = 0.0;
			// if(player_X >= GameLib.WIDTH) player_X = GameLib.WIDTH - 1;
			// if(player_Y < 25.0) player_Y = 25.0;
			// if(player_Y >= GameLib.HEIGHT) player_Y = GameLib.HEIGHT - 1;

			/*******************/
			/* Desenho da cena */
			/*******************/

			/* desenhando plano fundo */
			for (Background b : backs1)
				b.draw();
			for (Background b : backs2)
				b.draw();

			/* desenhando player */

			player.draw();

			/* deenhando projeteis (player) */

			for (Projectile p : player.getProjectiles()) {
				p.draw();
			}

			/*
			 * for (int i = 0; i < playerProjectiles.size(); i++){
			 * playerProjectiles.get(i).draw(); }
			 */

			/* desenhando projeteis (inimigos) */
			for (Enemy e : enemies1) {
				for (Projectile p : e.getProjectiles()) {
					p.draw();
				}
			}

			for (Enemy e : enemies2) {
				for (Projectile p : e.getProjectiles()) {
					p.draw();
				}
			}

			/*
			 * for (int i = 0; i < enemyProjectiles.size(); i++){
			 * enemyProjectiles.get(i).draw(); }
			 */

			/* desenhando inimigos (tipo 1) */

			for (int i = 0; i < enemies1.size(); i++) {
				enemies1.get(i).draw();
			}

			/* desenhando inimigos (tipo 2) */

			for (int i = 0; i < enemies2.size(); i++) {
				enemies2.get(i).draw();
			}

			/* desenhando inimigos (tipo 3) */

			for (Element e : enemies3) {
				e.draw();
			}

			/* desenhando powerups (tipo 1) */

			for (PowerUp1 p : powerUps1) {
				p.draw();
			}

			// /* chamama a display() da classe GameLib atualiza o desenho
			// exibido pela interface do jogo. */
			//
			GameLib.display();
			//
			// /* faz uma pausa de modo que cada execução do laço do main
			// loop demore aproximadamente 5 ms. */
			//
			busyWait(currentTime + 5);
		}
	}

	public List<Enemy1> getEnemies1() {
		return enemies1;
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

}
