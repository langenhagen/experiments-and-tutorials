// scripts/environment.js
import * as THREE from 'three';
import { createClouds, createHouses } from './utilities.js';

export function createEnvironment(scene) {
    createSky(scene);
    createSunWithGlare(scene);
    createGroundWithStreets(scene);
    createClouds(scene);
    const houses = createHouses(scene);
    return houses;
}

function createSky(scene) {
    const skyGeometry = new THREE.SphereGeometry(4000, 32, 32);
    const skyMaterial = new THREE.MeshBasicMaterial({
        color: 0x87CEEB,
        side: THREE.BackSide
    });
    const sky = new THREE.Mesh(skyGeometry, skyMaterial);
    scene.add(sky);
}

function createSunWithGlare(scene) {
    const sunLight = new THREE.DirectionalLight(0xffffff, 1);
    sunLight.position.set(1000, 2000, 1000);
    sunLight.castShadow = true;
    sunLight.shadow.mapSize.width = 2048;
    sunLight.shadow.mapSize.height = 2048;
    scene.add(sunLight);

    // Create a bright sphere to represent the sun
    const sunGeometry = new THREE.SphereGeometry(100, 32, 32);
    const sunMaterial = new THREE.MeshBasicMaterial({ color: 0xffffaa });
    const sun = new THREE.Mesh(sunGeometry, sunMaterial);
    sun.position.copy(sunLight.position);
    scene.add(sun);

    // Create a lens flare effect using sprites
    const flareColor = new THREE.Color(0xffffaa);
    const lensflare = new THREE.Group();

    for (let i = 0; i < 4; i++) {
        const flareGeometry = new THREE.PlaneGeometry(400 * (4 - i), 400 * (4 - i));
        const flareMaterial = new THREE.MeshBasicMaterial({
            color: flareColor,
            transparent: true,
            opacity: 0.1 * (4 - i),
            blending: THREE.AdditiveBlending,
            depthWrite: false
        });
        const flareMesh = new THREE.Mesh(flareGeometry, flareMaterial);
        flareMesh.position.copy(sunLight.position);
        lensflare.add(flareMesh);
    }

    scene.add(lensflare);

    // Ambient Light
    const ambientLight = new THREE.AmbientLight(0x404040, 1.5);
    scene.add(ambientLight);
}

function createGroundWithStreets(scene) {
    const gridSize = 2000;
    const streetWidth = 20;
    const streetCount = 10;

    // Create ground plane
    const groundGeometry = new THREE.PlaneGeometry(gridSize, gridSize);
    groundGeometry.rotateX(-Math.PI / 2);
    const groundMaterial = new THREE.MeshPhongMaterial({ color: 0x228B22 });
    const ground = new THREE.Mesh(groundGeometry, groundMaterial);
    ground.receiveShadow = true;
    scene.add(ground);

    // Create streets
    const streetMaterial = new THREE.MeshPhongMaterial({ color: 0x333333 });
    for (let i = -streetCount / 2; i <= streetCount / 2; i++) {
        // Vertical streets
        const streetGeometryV = new THREE.PlaneGeometry(streetWidth, gridSize);
        streetGeometryV.rotateX(-Math.PI / 2);
        const streetV = new THREE.Mesh(streetGeometryV, streetMaterial);
        streetV.position.x = (i * gridSize) / streetCount;
        streetV.position.y = 0.01; // Slightly above ground to prevent z-fighting
        streetV.receiveShadow = true;
        scene.add(streetV);

        // Horizontal streets
        const streetGeometryH = new THREE.PlaneGeometry(gridSize, streetWidth);
        streetGeometryH.rotateX(-Math.PI / 2);
        const streetH = new THREE.Mesh(streetGeometryH, streetMaterial);
        streetH.position.z = (i * gridSize) / streetCount;
        streetH.position.y = 0.01; // Slightly above ground
        streetH.receiveShadow = true;
        scene.add(streetH);

        // Add white stripes to vertical streets
        const stripeMaterial = new THREE.MeshBasicMaterial({ color: 0xffffff });
        const stripeGeometryV = new THREE.PlaneGeometry(2, gridSize);
        stripeGeometryV.rotateX(-Math.PI / 2);
        const stripeV = new THREE.Mesh(stripeGeometryV, stripeMaterial);
        stripeV.position.x = streetV.position.x;
        stripeV.position.y = 0.02; // Slightly above street to prevent z-fighting
        stripeV.receiveShadow = false;
        scene.add(stripeV);

        // Add white stripes to horizontal streets
        const stripeGeometryH = new THREE.PlaneGeometry(gridSize, 2);
        stripeGeometryH.rotateX(-Math.PI / 2);
        const stripeH = new THREE.Mesh(stripeGeometryH, stripeMaterial);
        stripeH.position.z = streetH.position.z;
        stripeH.position.y = 0.02; // Slightly above street
        stripeH.receiveShadow = false;
        scene.add(stripeH);
    }
}
