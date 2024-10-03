// scripts/main.js
import * as THREE from 'three';
import { OrbitControls } from 'OrbitControls';

import { createPlayer } from './player.js';
import { createEnvironment } from './environment.js';
import { createEnemies, updateEnemies } from './enemies.js';
import { setupControls, updateControls } from './controls.js';

let scene, camera, renderer, controls;
export let player;
let enemies = [];
let cars = [];
let clouds = [];
let explosions = [];
let houses = [];

let prevTime = performance.now();
const worldSize = 2000; // World size

init();
animate();

function init() {
    // Scene
    scene = new THREE.Scene();

    // Renderer
    renderer = new THREE.WebGLRenderer({ antialias: true });
    renderer.setSize(window.innerWidth, window.innerHeight);
    renderer.shadowMap.enabled = true;
    renderer.shadowMap.type = THREE.PCFSoftShadowMap;
    document.body.appendChild(renderer.domElement);

    // Camera
    camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 5000);

    // Controls
    controls = new OrbitControls(camera, renderer.domElement);
    controls.enableKeys = false;
    controls.enablePan = false;
    controls.enableZoom = false;

    // Create Player
    player = createPlayer();
    scene.add(player);

    // Create Environment (Ground, Sky, Sun, Clouds, Houses)
    houses = createEnvironment(scene);

    // Create Enemies (Cats, Cars)
    const enemyObjects = createEnemies(scene, player);
    enemies = enemyObjects.enemies;
    cars = enemyObjects.cars;
    explosions = enemyObjects.explosions;

    // Camera initial position
    camera.position.set(0, 20, -40);
    camera.lookAt(player.position);

    // Event Listeners
    setupControls();

    window.addEventListener('resize', onWindowResize);
}

function animate() {
    requestAnimationFrame(animate);

    const time = performance.now();
    const delta = (time - prevTime) / 1000;

    // Update Controls (Movement, Actions)
    updateControls(delta, player, camera, enemies, cars, explosions, houses, scene);

    // Update Enemies
    updateEnemies(delta, scene, player, enemies, cars, explosions);

    prevTime = time;

    renderer.render(scene, camera);
}

function onWindowResize() {
    camera.aspect = window.innerWidth / window.innerHeight;
    camera.updateProjectionMatrix();

    renderer.setSize(window.innerWidth, window.innerHeight);
}
