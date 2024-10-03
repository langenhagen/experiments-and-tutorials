// scripts/controls.js
import * as THREE from 'three';
import { animateLegs, resetLegs, animateSitting } from './player.js';
import { checkSpinCollision } from './enemies.js';
import { player } from './main.js';

let moveForward = false;
let moveBackward = false;
let moveLeft = false;
let moveRight = false;
let rotateLeft = false;
let rotateRight = false;
let isSpinning = false;
let isSprinting = false;
let isJumping = false;
let jumpVelocity = 0;
let spinDuration = 0.3; // seconds
let spinElapsed = 0;

let velocity = new THREE.Vector3();


export function setupControls() {
    document.addEventListener('keydown', onKeyDown);
    document.addEventListener('keyup', onKeyUp);
}

export function updateControls(delta, player, camera, enemies, cars, explosions, houses, scene) {
    velocity.set(0, 0, 0);

    let speed = 100;
    if (isSprinting) speed = 200; // Increase speed when sprinting

    const rotationSpeed = Math.PI * 2 * delta; // Faster rotation

    if (moveForward) velocity.z += speed * delta;
    if (moveBackward) velocity.z -= speed * delta;
    if (moveLeft) velocity.x += speed * delta;
    if (moveRight) velocity.x -= speed * delta;

    if (rotateLeft) player.rotation.y += rotationSpeed;
    if (rotateRight) player.rotation.y -= rotationSpeed;

    // Spin Attack
    if (isSpinning) {
        const spinSpeed = (2 * Math.PI) / spinDuration;
        spinElapsed += delta;
        player.rotation.y += spinSpeed * delta;

        // Animate legs during spin
        player.userData.walkTime += delta;
        animateLegs(player, delta);

        // Check for nearby enemies and cars
        checkSpinCollision(player, enemies, cars, explosions, scene);

        if (spinElapsed >= spinDuration) {
            isSpinning = false;
        }
    }

    // Jumping
    if (isJumping) {
        console.log("kp")
        player.position.y += jumpVelocity * delta;
        jumpVelocity -= 600 * delta; // Gravity

        if (player.position.y <= 0) {
            player.position.y = 0;
            isJumping = false;
            jumpVelocity = 0;
        }
    }

    // Move in the direction the player is facing
    const direction = new THREE.Vector3(velocity.x, 0, velocity.z);
    direction.applyAxisAngle(new THREE.Vector3(0, 1, 0), player.rotation.y);

    player.position.add(direction);

    // Collision detection with houses
    houses.forEach((house) => {
        const playerBox = new THREE.Box3().setFromObject(player);
        const houseBox = house.userData.boundingBox.clone();
        houseBox.translate(house.position);

        if (playerBox.intersectsBox(houseBox)) {
            // Collided with house, move back
            player.position.sub(direction);
        }
    });

    // Determine if the player is moving
    const isMoving = direction.length() > 0;

    // Animate legs when moving (and not spinning)
    if (!isSpinning) {
        if (isMoving) {
            player.userData.walkTime += delta;
            animateLegs(player, delta);
            if (!isJumping) {
                animateSitting(player, delta, false); // Stand up
            }
        } else {
            resetLegs(player);
            if (!isJumping) {
                animateSitting(player, delta, true); // Sit down
            }
        }
    }

    // Update camera position to follow the player
    const relativeCameraOffset = new THREE.Vector3(0, 10, -20);
    let cameraOffset;

    if (isSpinning) {
        // Keep camera steady during spin attack
        const playerPosition = player.position.clone();
        cameraOffset = playerPosition.add(relativeCameraOffset);
    } else {
        // Follow player rotation
        cameraOffset = relativeCameraOffset.applyMatrix4(player.matrixWorld);
    }

    camera.position.lerp(cameraOffset, 0.1);
    camera.lookAt(player.position);
}

function onKeyDown(event) {
    switch (event.code) {
        case 'ArrowUp':
        case 'KeyW':
            moveForward = true;
            break;

        case 'ArrowLeft':
        case 'KeyA':
            moveLeft = true;
            break;

        case 'ArrowDown':
        case 'KeyS':
            moveBackward = true;
            break;

        case 'ArrowRight':
        case 'KeyD':
            moveRight = true;
            break;

        case 'PageUp':
        case 'KeyQ':
            rotateLeft = true;
            break;

        case 'PageDown':
        case 'KeyE':
            rotateRight = true;
            break;

        case 'ShiftLeft':
        case 'ShiftRight':
            isSprinting = true;
            break;

        case 'ControlLeft':
        case 'ControlRight':
            if (!isJumping && player.position.y <= 0.1) {
                isJumping = true;
                jumpVelocity = 300;
            }
            break;

        case 'Space':
            if (!isSpinning) {
                isSpinning = true;
                spinElapsed = 0;
            }
            break;
    }
}

function onKeyUp(event) {
    switch (event.code) {
        case 'ArrowUp':
        case 'KeyW':
            moveForward = false;
            break;

        case 'ArrowLeft':
        case 'KeyA':
            moveLeft = false;
            break;

        case 'ArrowDown':
        case 'KeyS':
            moveBackward = false;
            break;

        case 'ArrowRight':
        case 'KeyD':
            moveRight = false;
            break;

        case 'PageUp':
        case 'KeyQ':
            rotateLeft = false;
            break;

        case 'PageDown':
        case 'KeyE':
            rotateRight = false;
            break;

        case 'ShiftLeft':
        case 'ShiftRight':
            isSprinting = false;
            break;
    }
}
