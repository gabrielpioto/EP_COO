package projetocoo;
/* oi*/
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import projetocoo.model.Background;
import projetocoo.model.Enemy1;
import projetocoo.model.Enemy2;
import projetocoo.model.Player;
import projetocoo.model.base.Enemy;
import projetocoo.model.projectile.Projectile;

public class MainGame {
	
	private static MainGame mainGame = new MainGame();
	private static final int NUMBER_ENEMIES_1 = 10;
	private static final int NUMBER_ENEMIES_2 = 10;
	private static final int NUMBER_BACKS_1 = 20;
	private static final int NUMBER_BACKS_2 = 50;
	private static final int NUMBER_PROJECTILES = 200;
	private static final int NUMBER_e_PROJECTILES = 200;
	
	/* Indica que o jogo está em execução */
	boolean running = true;

	/* variáveis usadas no controle de tempo efetuado no main loop */

	private long delta;
	private long currentTime;

	private Player player = new Player(GameLib.WIDTH / 2,
			GameLib.HEIGHT * 0.90, 0.25, 0.25, 12, currentTime);

	// Enemy1 enemy1 = new Enemy1(9.0, 2000);
	// Enemy2 enemy2 = new Enemy2(12.0, 7000, GameLib.WIDTH * 0.20, 0);
	private List<Enemy1> enemies1 = new ArrayList<Enemy1>(NUMBER_ENEMIES_1);
	
	private List<Enemy2> enemies2 = new ArrayList<Enemy2>(NUMBER_ENEMIES_2);

	private List<Background> backs1 = new ArrayList<Background>(NUMBER_BACKS_1);

	private List<Background> backs2 = new ArrayList<Background>(NUMBER_BACKS_2);
	
	private List<Projectile> playerProjectiles= new ArrayList<Projectile>(NUMBER_PROJECTILES);
	
	private List<Projectile> enemyProjectiles= new ArrayList<Projectile>(NUMBER_e_PROJECTILES);
	
	
	
	
	public MainGame() {

		currentTime = System.currentTimeMillis();
		
		for (int i = 0; i < NUMBER_ENEMIES_1; i++) {
			enemies1.add(new Enemy1(9.0, 2000));
		}

		for (int i = 0; i < NUMBER_ENEMIES_2; i++) {
			enemies2.add(new Enemy2(12.0, 7000, GameLib.WIDTH * 0.20, 0));
		}

		for (int i = 0; i < NUMBER_BACKS_1; i++) {
			double x = Math.random() * GameLib.WIDTH;
			double y = Math.random() * GameLib.HEIGHT;
			backs1.add(new Background(x, y, 0.07, 0.0, 3, Color.GRAY));
		}
		
		for (int i = 0; i < NUMBER_BACKS_2; i++) {
			double x = Math.random() * GameLib.WIDTH;
			double y = Math.random() * GameLib.HEIGHT;
			backs2.add(new Background(x, y, 0.045, 0.0, 2, Color.DARK_GRAY));
		}
		
		for (int i = 0; i < NUMBER_PROJECTILES; i++){
			playerProjectiles.add(new Projectile(0, 0, 0, 0, Color.GREEN));
		}
		
		for (int i = 0; i < NUMBER_e_PROJECTILES; i++){
			enemyProjectiles.add(new Projectile(0, 0, 0, 0, Color.RED));
		}
		
		// TODO checar os parametros que envio para criar um inimigo
		for (int i = 0; i < NUMBER_ENEMIES_1; i++){
			enemies1.add(new Enemy1(9.0, (int) (getCurrentTime() + 2000)));
		}
		
		for (int i = 0; i < NUMBER_ENEMIES_2; i++){
			enemies2.add(new Enemy2(0, 0, 0, 0));
		}
//		
//		System.exit(0);
//
	}
	
	public void run(){
		
		/* iniciado interface gráfica */
		GameLib.initGraphics();
		
//		
//		/*************************************************************************************************/
//		/*                                                                                               */
//		/* Main loop do jogo                                                                             */
//		/*                                                                                               */
//		/* O main loop do jogo possui executa as seguintes operações:                                    */
//		/*                                                                                               */
//		/* 1) Verifica se há colisões e atualiza estados dos elementos conforme a necessidade.           */
//		/*                                                                                               */
//		/* 2) Atualiza estados dos elementos baseados no tempo que correu desde a última atualização     */
//		/*    e no timestamp atual: posição e orientação, execução de disparos de projéteis, etc.        */
//		/*                                                                                               */
//		/* 3) Processa entrada do usuário (teclado) e atualiza estados do player conforme a necessidade. */
//		/*                                                                                               */
//		/* 4) Desenha a cena, a partir dos estados dos elementos.                                        */
//		/*                                                                                               */
//		/* 5) Espera um período de tempo (de modo que delta seja aproximadamente sempre constante).      */
//		/*                                                                                               */
//		/*************************************************************************************************/
//		
		while(running){
//		
//			/* Usada para atualizar o estado dos elementos do jogo    */
//			/* (player, projéteis e inimigos) "delta" indica quantos  */
//			/* ms se passaram desde a última atualização.             */
//			
			delta = System.currentTimeMillis() - currentTime;
//			
//			/* Já a variável "currentTime" nos dá o timestamp atual.  */
//			
			currentTime = System.currentTimeMillis();
//			
//			/***************************/
//			/* Verificação de colisões */
//			/***************************/
			
			for(Enemy enemy : enemies1){
				player.checkCollision(enemy.getProjectiles());
				enemy.checkCollision(player.getProjectiles());
			}
			
			for(Enemy enemy : enemies2){
				player.checkCollision(enemy.getProjectiles());
				enemy.checkCollision(player.getProjectiles());
			}			
			
			player.checkCollision(enemies1);
			player.checkCollision(enemies2);
			
//						
//			if(player_state == ACTIVE){
//				
//				/* colisões player - projeteis (inimigo) */
//				
//				for(int i = 0; i < e_projectile_states.length; i++){
//					
//					double dx = e_projectile_X[i] - player_X;
//					double dy = e_projectile_Y[i] - player_Y;
//					double dist = Math.sqrt(dx * dx + dy * dy);
//					
//					if(dist < (player_radius + e_projectile_radius) * 0.8){
//						
//						player_state = EXPLODING;
//						player_explosion_start = currentTime;
//						player_explosion_end = currentTime + 2000;
//					}
//				}
//			
//				/* colisões player - inimigos */
//							
//				for(int i = 0; i < enemy1_states.length; i++){
//					
//					double dx = enemy1_X[i] - player_X;
//					double dy = enemy1_Y[i] - player_Y;
//					double dist = Math.sqrt(dx * dx + dy * dy);
//					
//					if(dist < (player_radius + enemy1_radius) * 0.8){
//						
//						player_state = EXPLODING;
//						player_explosion_start = currentTime;
//						player_explosion_end = currentTime + 2000;
//					}
//				}
//				
//				for(int i = 0; i < enemy2_states.length; i++){
//					
//					double dx = enemy2_X[i] - player_X;
//					double dy = enemy2_Y[i] - player_Y;
//					double dist = Math.sqrt(dx * dx + dy * dy);
//					
//					if(dist < (player_radius + enemy2_radius) * 0.8){
//						
//						player_state = EXPLODING;
//						player_explosion_start = currentTime;
//						player_explosion_end = currentTime + 2000;
//					}
//				}
//			}
//			
//			/* colisões projeteis (player) - inimigos */
//			
//			for(int k = 0; k < projectile_states.length; k++){
//				
//				for(int i = 0; i < enemy1_states.length; i++){
//										
//					if(enemy1_states[i] == ACTIVE){
//					
//						double dx = enemy1_X[i] - projectile_X[k];
//						double dy = enemy1_Y[i] - projectile_Y[k];
//						double dist = Math.sqrt(dx * dx + dy * dy);
//						
//						if(dist < enemy1_radius){
//							
//							enemy1_states[i] = EXPLODING;
//							enemy1_explosion_start[i] = currentTime;
//							enemy1_explosion_end[i] = currentTime + 500;
//						}
//					}
//				}
//				
//				for(int i = 0; i < enemy2_states.length; i++){
//					
//					if(enemy2_states[i] == ACTIVE){
//						
//						double dx = enemy2_X[i] - projectile_X[k];
//						double dy = enemy2_Y[i] - projectile_Y[k];
//						double dist = Math.sqrt(dx * dx + dy * dy);
//						
//						if(dist < enemy2_radius){
//							
//							enemy2_states[i] = EXPLODING;
//							enemy2_explosion_start[i] = currentTime;
//							enemy2_explosion_end[i] = currentTime + 500;
//						}
//					}
//				}
//			}
//				
//			/***************************/
//			/* Atualizações de estados */
//			/***************************/
//			
//			/* projeteis (player) */
			
			
//			
//			for(int i = 0; i < projectile_states.length; i++){
//				
//				if(projectile_states[i] == ACTIVE){
//					
//					/* verificando se projétil saiu da tela */
//					if(projectile_Y[i] < 0) {
//						
//						projectile_states[i] = INACTIVE;
//					}
//					else {
//					
//						projectile_X[i] += projectile_VX[i] * delta;
//						projectile_Y[i] += projectile_VY[i] * delta;
//					}
//				}
//			}
//			
//			/* projeteis (inimigos) */
//			
//			for(int i = 0; i < e_projectile_states.length; i++){
//				
//				if(e_projectile_states[i] == ACTIVE){
//					
//					/* verificando se projétil saiu da tela */
//					if(e_projectile_Y[i] > GameLib.HEIGHT) {
//						
//						e_projectile_states[i] = INACTIVE;
//					}
//					else {
//					
//						e_projectile_X[i] += e_projectile_VX[i] * delta;
//						e_projectile_Y[i] += e_projectile_VY[i] * delta;
//					}
//				}
//			}
//			
//			/* inimigos tipo 1 */
//			
//			for(int i = 0; i < enemy1_states.length; i++){
//				
//				if(enemy1_states[i] == EXPLODING){
//					
//					if(currentTime > enemy1_explosion_end[i]){
//						
//						enemy1_states[i] = INACTIVE;
//					}
//				}
//				
//				if(enemy1_states[i] == ACTIVE){
//					
//					/* verificando se inimigo saiu da tela */
//					if(enemy1_Y[i] > GameLib.HEIGHT + 10) {
//						
//						enemy1_states[i] = INACTIVE;
//					}
//					else {
//					
//						enemy1_X[i] += enemy1_V[i] * Math.cos(enemy1_angle[i]) * delta;
//						enemy1_Y[i] += enemy1_V[i] * Math.sin(enemy1_angle[i]) * delta * (-1.0);
//						enemy1_angle[i] += enemy1_RV[i] * delta;
//						
//						if(currentTime > enemy1_nextShoot[i] && enemy1_Y[i] < player_Y){
//																							
//							int free = findFreeIndex(e_projectile_states);
//							
//							if(free < e_projectile_states.length){
//								
//								e_projectile_X[free] = enemy1_X[i];
//								e_projectile_Y[free] = enemy1_Y[i];
//								e_projectile_VX[free] = Math.cos(enemy1_angle[i]) * 0.45;
//								e_projectile_VY[free] = Math.sin(enemy1_angle[i]) * 0.45 * (-1.0);
//								e_projectile_states[free] = 1;
//								
//								enemy1_nextShoot[i] = (long) (currentTime + 200 + Math.random() * 500);
//							}
//						}
//					}
//				}
//			}
//			
//			/* inimigos tipo 2 */
//			
//			for(int i = 0; i < enemy2_states.length; i++){
//				
//				if(enemy2_states[i] == EXPLODING){
//					
//					if(currentTime > enemy2_explosion_end[i]){
//						
//						enemy2_states[i] = INACTIVE;
//					}
//				}
//				
//				if(enemy2_states[i] == ACTIVE){
//					
//					/* verificando se inimigo saiu da tela */
//					if(	enemy2_X[i] < -10 || enemy2_X[i] > GameLib.WIDTH + 10 ) {
//						
//						enemy2_states[i] = INACTIVE;
//					}
//					else {
//						
//						boolean shootNow = false;
//						double previousY = enemy2_Y[i];
//												
//						enemy2_X[i] += enemy2_V[i] * Math.cos(enemy2_angle[i]) * delta;
//						enemy2_Y[i] += enemy2_V[i] * Math.sin(enemy2_angle[i]) * delta * (-1.0);
//						enemy2_angle[i] += enemy2_RV[i] * delta;
//						
//						double threshold = GameLib.HEIGHT * 0.30;
//						
//						if(previousY < threshold && enemy2_Y[i] >= threshold) {
//							
//							if(enemy2_X[i] < GameLib.WIDTH / 2) enemy2_RV[i] = 0.003;
//							else enemy2_RV[i] = -0.003;
//						}
//						
//						if(enemy2_RV[i] > 0 && Math.abs(enemy2_angle[i] - 3 * Math.PI) < 0.05){
//							
//							enemy2_RV[i] = 0.0;
//							enemy2_angle[i] = 3 * Math.PI;
//							shootNow = true;
//						}
//						
//						if(enemy2_RV[i] < 0 && Math.abs(enemy2_angle[i]) < 0.05){
//							
//							enemy2_RV[i] = 0.0;
//							enemy2_angle[i] = 0.0;
//							shootNow = true;
//						}
//																		
//						if(shootNow){
//
//							double [] angles = { Math.PI/2 + Math.PI/8, Math.PI/2, Math.PI/2 - Math.PI/8 };
//							int [] freeArray = findFreeIndex(e_projectile_states, angles.length);
//
//							for(int k = 0; k < freeArray.length; k++){
//								
//								int free = freeArray[k];
//								
//								if(free < e_projectile_states.length){
//									
//									double a = angles[k] + Math.random() * Math.PI/6 - Math.PI/12;
//									double vx = Math.cos(a);
//									double vy = Math.sin(a);
//										
//									e_projectile_X[free] = enemy2_X[i];
//									e_projectile_Y[free] = enemy2_Y[i];
//									e_projectile_VX[free] = vx * 0.30;
//									e_projectile_VY[free] = vy * 0.30;
//									e_projectile_states[free] = 1;
//								}
//							}
//						}
//					}
//				}
//			}
//			
//			/* verificando se novos inimigos (tipo 1) devem ser "lançados" */
//			
//			if(currentTime > nextEnemy1){
//				
//				int free = findFreeIndex(enemy1_states);
//								
//				if(free < enemy1_states.length){
//					
//					enemy1_X[free] = Math.random() * (GameLib.WIDTH - 20.0) + 10.0;
//					enemy1_Y[free] = -10.0;
//					enemy1_V[free] = 0.20 + Math.random() * 0.15;
//					enemy1_angle[free] = 3 * Math.PI / 2;
//					enemy1_RV[free] = 0.0;
//					enemy1_states[free] = ACTIVE;
//					enemy1_nextShoot[free] = currentTime + 500;
//					nextEnemy1 = currentTime + 500;
//				}
//			}
//			
//			/* verificando se novos inimigos (tipo 2) devem ser "lançados" */
//			
//			if(currentTime > nextEnemy2){
//				
//				int free = findFreeIndex(enemy2_states);
//								
//				if(free < enemy2_states.length){
//					
//					enemy2_X[free] = enemy2_spawnX;
//					enemy2_Y[free] = -10.0;
//					enemy2_V[free] = 0.42;
//					enemy2_angle[free] = (3 * Math.PI) / 2;
//					enemy2_RV[free] = 0.0;
//					enemy2_states[free] = ACTIVE;
//
//					enemy2_count++;
//					
//					if(enemy2_count < 10){
//						
//						nextEnemy2 = currentTime + 120;
//					}
//					else {
//						
//						enemy2_count = 0;
//						enemy2_spawnX = Math.random() > 0.5 ? GameLib.WIDTH * 0.2 : GameLib.WIDTH * 0.8;
//						nextEnemy2 = (long) (currentTime + 3000 + Math.random() * 3000);
//					}
//				}
//			}
//			
//			/* Verificando se a explosão do player já acabou.         */
//			/* Ao final da explosão, o player volta a ser controlável */
//			if(player_state == EXPLODING){
//				
//				if(currentTime > player_explosion_end){
//					
//					player_state = ACTIVE;
//				}
//			}
//			
//			/********************************************/
//			/* Verificando entrada do usuário (teclado) */
//			/********************************************/
//			
//			if(player_state == ACTIVE){
//				
//				if(GameLib.iskeyPressed(GameLib.KEY_UP)) player_Y -= delta * player_VY;
//				if(GameLib.iskeyPressed(GameLib.KEY_DOWN)) player_Y += delta * player_VY;
//				if(GameLib.iskeyPressed(GameLib.KEY_LEFT)) player_X -= delta * player_VX;
//				if(GameLib.iskeyPressed(GameLib.KEY_RIGHT)) player_X += delta * player_VY;
//				if(GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {
//					
//					if(currentTime > player_nextShot){
//						
//						int free = findFreeIndex(projectile_states);
//												
//						if(free < projectile_states.length){
//							
//							projectile_X[free] = player_X;
//							projectile_Y[free] = player_Y - 2 * player_radius;
//							projectile_VX[free] = 0.0;
//							projectile_VY[free] = -1.0;
//							projectile_states[free] = 1;
//							player_nextShot = currentTime + 100;
//						}
//					}	
//				}
//			}
//			
//			if(GameLib.iskeyPressed(GameLib.KEY_ESCAPE)) running = false;
//			
//			/* Verificando se coordenadas do player ainda estão dentro	*/
//			/* da tela de jogo após processar entrada do usuário.       */
//			
//			if(player_X < 0.0) player_X = 0.0;
//			if(player_X >= GameLib.WIDTH) player_X = GameLib.WIDTH - 1;
//			if(player_Y < 25.0) player_Y = 25.0;
//			if(player_Y >= GameLib.HEIGHT) player_Y = GameLib.HEIGHT - 1;

			/*******************/
			/* Desenho da cena */
			/*******************/
	
			/* desenhando plano fundo  */	
			for (int i = 0; i < backs1.size(); i++){
				backs1.get(i).draw();
			}
			
			for (int i = 0; i < backs2.size(); i++){
				backs2.get(i).draw();
			}
			
			/* desenhando player */
			
			player.draw();

			/* deenhando projeteis (player) */	
			
			for (int i = 0; i < playerProjectiles.size(); i++){
				playerProjectiles.get(i).draw();
			}

			/* desenhando projeteis (inimigos) */
			
			for (int i = 0; i < enemyProjectiles.size(); i++){
				enemyProjectiles.get(i).draw();
			}	

			/* desenhando inimigos (tipo 1) */

			for(int i = 0; i < enemies1.size(); i++){
				enemies1.get(i).draw();
			}
			
			/* desenhando inimigos (tipo 2) */
		
			for(int i = 0; i < enemies2.size(); i++){
				enemies2.get(i).draw();
			}
		
//			/* chamama a display() da classe GameLib atualiza o desenho exibido pela interface do jogo. */
//			
			GameLib.display();
//			
//			/* faz uma pausa de modo que cada execução do laço do main loop demore aproximadamente 5 ms. */
//			
//			busyWait(currentTime + 5);
		}
	}
	
	public long getDelta(){
		return delta;
	}
	
	public long getCurrentTime(){
		return currentTime;
	}

	public static MainGame getInstance(){
		return mainGame;
	}

	public Player getPlayer() {
		return player;
	}
	
	
}
