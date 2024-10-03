// scripts/enemies.js
import * as THREE from 'three';
import { createCat } from './player.js';
import { createCar } from './utilities.js';
import { animateLegs } from './player.js';

let enemyRespawnTime = 5; // seconds

export function createEnemies(scene, player) {
    let enemies = [];
    let cars = [];
    let explosions = [];

    spawnEnemyCats(scene, player, enemies);
    spawnCars(scene, cars);

    return { enemies, cars, explosions };
}

function spawnEnemyCats(scene, player, enemies) {
    const enemyCount = 10;
    for (let i = 0; i < enemyCount; i++) {
        const enemyCat = createCat(0xff0000); // Red color
        enemyCat.position.set(
            player.position.x + (Math.random() - 0.5) * 500,
            0,
            player.position.z + (Math.random() - 0.5) * 500
        );
        scene.add(enemyCat);
        enemies.push(enemyCat);
    }
}

function spawnCars(scene, cars) {
    const carCount = 40; // Increased number of cars
    const streetCount = 10;
    const gridSize = 2000;
    const streetSpacing = gridSize / streetCount;

    for (let i = 0; i < carCount; i++) {
        const car = createCar();

        // Randomly choose vertical or horizontal street
        const isVertical = Math.random() < 0.5;

        // Randomly choose a street index
        const streetIndex = Math.floor(Math.random() * streetCount) - streetCount / 2;

        // Position the car on the street
        const position = new THREE.Vector3();
        const velocity = new THREE.Vector3();

        if (isVertical) {
            // Vertical street (along Z-axis)
            position.x = streetIndex * streetSpacing;
            position.y = 0; // Adjusted for car height
            position.z = -gridSize / 2 - Math.random() * 1000; // Start off-screen
            velocity.z = 100 + Math.random() * 100; // Move along Z-axis
            car.rotation.y = 0; // Face forward along Z-axis
        } else {
            // Horizontal street (along X-axis)
            position.x = -gridSize / 2 - Math.random() * 1000; // Start off-screen
            position.y = 0;
            position.z = streetIndex * streetSpacing;
            velocity.x = 100 + Math.random() * 100; // Move along X-axis
            car.rotation.y = Math.PI / 2; // Face forward along X-axis
        }

        car.position.copy(position);
        car.userData = {
            velocity: velocity,
            exploding: false,
            explodeTime: 0,
            isVertical: isVertical
        };
        scene.add(car);
        cars.push(car);
    }
}

export function updateEnemies(delta, scene, player, enemies, cars, explosions) {
    // Update enemy cats
    enemies.forEach((enemy, index) => {
        const dirToPlayer = new THREE.Vector3();
        dirToPlayer.subVectors(player.position, enemy.position).normalize();

        enemy.position.addScaledVector(dirToPlayer, 50 * delta);

        // Rotate enemy to face the player
        const enemyDirection = dirToPlayer.clone();
        const angle = Math.atan2(enemyDirection.x, enemyDirection.z);
        enemy.rotation.y = angle;

        // Animate enemy legs
        enemy.userData.walkTime += delta;
        enemy.userData.walkTime = enemy.userData.walkTime % (2 * Math.PI);
        animateLegs(enemy, delta);

        // Collision detection with player
        const enemyBox = new THREE.Box3().setFromObject(enemy);
        const playerBox = new THREE.Box3().setFromObject(player);

        if (enemyBox.intersectsBox(playerBox)) {
            // Enemy cat is hit
            createExplosion(enemy.position, explosions, scene);
            scene.remove(enemy);
            enemies.splice(index, 1);

            // Schedule respawn
            setTimeout(() => {
                const newEnemy = createCat(0xff0000);
                newEnemy.position.set(
                    player.position.x + (Math.random() - 0.5) * 500,
                    0,
                    player.position.z + (Math.random() - 0.5) * 500
                );
                scene.add(newEnemy);
                enemies.push(newEnemy);
            }, enemyRespawnTime * 1000);
        }
    });

    // Update enemy cars
    cars.forEach((car, index) => {
        if (!car.userData.exploding) {
            const velocity = car.userData.velocity.clone();
            car.position.addScaledVector(velocity, delta);

            // Rotate car to face movement direction
            if (velocity.length() > 0) {
                const angle = Math.atan2(velocity.x, velocity.z);
                car.rotation.y = angle;
            }

            // Loop cars back to start
            if (car.userData.isVertical) {
                if (car.position.z > 2000 / 2 + 100) {
                    car.position.z = -2000 / 2 - Math.random() * 1000;
                }
            } else {
                if (car.position.x > 2000 / 2 + 100) {
                    car.position.x = -2000 / 2 - Math.random() * 1000;
                }
            }

            // Collision detection with player
            const carBox = new THREE.Box3().setFromObject(car);
            const playerBox = new THREE.Box3().setFromObject(player);

            if (carBox.intersectsBox(playerBox)) {
                // Car "explodes"
                car.userData.exploding = true;
                car.userData.explodeTime = 0;
                car.userData.velocity.set(0, 300, 0); // Jump up
                createExplosion(car.position, explosions, scene);
            }
        } else {
            // Explosion animation: car jumps up and falls down
            car.userData.velocity.y -= 600 * delta; // Gravity effect
            car.position.addScaledVector(car.userData.velocity, delta);
            car.userData.explodeTime += delta;

            if (car.position.y <= 2.5) {
                car.position.y = 2.5;
                scene.remove(car);
                cars.splice(index, 1);
            }
        }
    });

    // Update explosions
    updateExplosions(delta, explosions, scene);
}

function createExplosion(position, explosions, scene) {
    const explosionGroup = new THREE.Group();
    const particleCount = 50;

    for (let i = 0; i < particleCount; i++) {
        const particleGeometry = new THREE.SphereGeometry(0.5, 8, 8);
        const particleMaterial = new THREE.MeshBasicMaterial({ color: 0xffa500 });
        const particle = new THREE.Mesh(particleGeometry, particleMaterial);

        particle.position.copy(position);
        particle.userData = {
            velocity: new THREE.Vector3(
                (Math.random() - 0.5) * 100,
                (Math.random() - 0.5) * 100,
                (Math.random() - 0.5) * 100
            ),
            life: 1.0
        };

        explosionGroup.add(particle);
    }

    explosions.push(explosionGroup);
    scene.add(explosionGroup);
}

function updateExplosions(delta, explosions, scene) {
    explosions.forEach((explosionGroup, index) => {
        explosionGroup.children.forEach((particle) => {
            particle.userData.life -= delta;
            if (particle.userData.life <= 0) {
                explosionGroup.remove(particle);
            } else {
                particle.position.addScaledVector(particle.userData.velocity, delta);
                particle.material.opacity = particle.userData.life;
                particle.material.transparent = true;
            }
        });

        if (explosionGroup.children.length === 0) {
            scene.remove(explosionGroup);
            explosions.splice(index, 1);
        }
    });
}



export function checkSpinCollision(player, enemies, cars, explosions, scene) {
    // Check for collisions with enemies and cars during spin attack
    enemies.forEach((enemy, index) => {
        const distance = player.position.distanceTo(enemy.position);
        if (distance < 20) {
            // Enemy cat is hit
            createExplosion(enemy.position, explosions, scene);
            scene.remove(enemy);
            enemies.splice(index, 1);

            // Schedule respawn
            setTimeout(() => {
                const newEnemy = createCat(0xff0000);
                newEnemy.position.set(
                    player.position.x + (Math.random() - 0.5) * 500,
                    0,
                    player.position.z + (Math.random() - 0.5) * 500
                );
                scene.add(newEnemy);
                enemies.push(newEnemy);
            }, enemyRespawnTime * 1000);
        }
    });
}