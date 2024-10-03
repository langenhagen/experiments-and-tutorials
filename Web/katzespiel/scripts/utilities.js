// scripts/utilities.js
import * as THREE from 'three';

export function createClouds(scene) {
    const cloudCount = 50;
    for (let i = 0; i < cloudCount; i++) {
        const cloud = createCloud();
        cloud.position.set(
            (Math.random() - 0.5) * 4000,
            800 + Math.random() * 500,
            (Math.random() - 0.5) * 4000
        );
        cloud.rotation.y = Math.random() * Math.PI * 2;
        scene.add(cloud);
    }
}

function createCloud() {
    const cloud = new THREE.Group();
    const cloudMaterial = new THREE.MeshPhongMaterial({
        color: 0xffffff,
        flatShading: false,
        opacity: 0.9,
        transparent: true
    });

    const puffCount = 5 + Math.floor(Math.random() * 5);
    for (let i = 0; i < puffCount; i++) {
        const puffGeometry = new THREE.SphereGeometry(50, 16, 16);
        const puff = new THREE.Mesh(puffGeometry, cloudMaterial);
        puff.position.set(
            (Math.random() - 0.5) * 100,
            (Math.random() - 0.5) * 50,
            (Math.random() - 0.5) * 100
        );
        puff.castShadow = true;
        puff.receiveShadow = true;
        cloud.add(puff);
    }

    // Make the cloud face down
    cloud.rotation.x = Math.PI / 2;

    return cloud;
}

export function createHouses(scene) {
    const houseCount = 20;
    const gridSize = 2000;
    const streetCount = 10;
    const cellSize = gridSize / streetCount;
    const housePositions = [];
    const houses = []; // Array to store houses

    for (let i = -streetCount / 2; i < streetCount / 2; i++) {
        for (let j = -streetCount / 2; j < streetCount / 2; j++) {
            // Calculate the center position of each cell
            const x = (i + 0.5) * cellSize;
            const z = (j + 0.5) * cellSize;
            housePositions.push({ x, z });
        }
    }

    // Randomly select positions for houses
    for (let i = 0; i < houseCount; i++) {
        if (housePositions.length === 0) break;

        const index = Math.floor(Math.random() * housePositions.length);
        const position = housePositions.splice(index, 1)[0];

        const house = createHouse();
        // Slight random offset within the cell to avoid uniformity
        house.position.set(
            position.x + (Math.random() - 0.5) * (cellSize / 2 - 20),
            0,
            position.z + (Math.random() - 0.5) * (cellSize / 2 - 20)
        );
        scene.add(house);
        houses.push(house); // Add house to array
    }

    return houses; // Return the array of houses
}

function createHouse() {
    const houseGroup = new THREE.Group();

    // Randomize house dimensions
    const houseWidth = 30 + Math.random() * 20;
    const houseHeight = 20 + Math.random() * 20;
    const houseDepth = 20 + Math.random() * 20;

    // Randomize colors
    const wallColors = [0xffffff, 0xffe4b5, 0xffdab9, 0xfffacd];
    const roofColors = [0xcd5c5c, 0x8b4513, 0x708090];
    const wallColor = wallColors[Math.floor(Math.random() * wallColors.length)];
    const roofColor = roofColors[Math.floor(Math.random() * roofColors.length)];

    // Base
    const baseGeometry = new THREE.BoxGeometry(houseWidth, 2, houseDepth);
    const baseMaterial = new THREE.MeshPhongMaterial({ color: 0x8B4513 });
    const base = new THREE.Mesh(baseGeometry, baseMaterial);
    base.position.y = 1;
    base.castShadow = true;
    base.receiveShadow = true;
    houseGroup.add(base);

    // Walls
    const wallGeometry = new THREE.BoxGeometry(houseWidth, houseHeight, houseDepth);
    const wallMaterial = new THREE.MeshPhongMaterial({ color: wallColor });
    const walls = new THREE.Mesh(wallGeometry, wallMaterial);
    walls.position.y = houseHeight / 2 + 2;
    walls.castShadow = true;
    walls.receiveShadow = true;
    houseGroup.add(walls);

    // Windows with frames
    const windowGeometry = new THREE.BoxGeometry(5, 5, 0.5);
    const windowMaterial = new THREE.MeshPhongMaterial({ color: 0x87CEEB, transparent: true, opacity: 0.6 });
    const frameMaterial = new THREE.MeshPhongMaterial({ color: 0x654321 });

    const windowPositions = [
        [-houseWidth / 4, houseHeight / 2, houseDepth / 2 + 0.26],
        [houseWidth / 4, houseHeight / 2, houseDepth / 2 + 0.26],
        [-houseWidth / 4, houseHeight / 2, -houseDepth / 2 - 0.26],
        [houseWidth / 4, houseHeight / 2, -houseDepth / 2 - 0.26]
    ];

    windowPositions.forEach(pos => {
        const windowFrame = new THREE.Mesh(new THREE.BoxGeometry(6, 6, 0.5), frameMaterial);
        windowFrame.position.set(pos[0], pos[1], pos[2]);
        houseGroup.add(windowFrame);

        const windowMesh = new THREE.Mesh(windowGeometry, windowMaterial);
        windowMesh.position.set(pos[0], pos[1], pos[2] + (pos[2] > 0 ? -0.01 : 0.01));
        houseGroup.add(windowMesh);
    });

    // Door with knob
    const doorGeometry = new THREE.BoxGeometry(6, 10, 1);
    const doorMaterial = new THREE.MeshPhongMaterial({ color: 0x654321 });
    const door = new THREE.Mesh(doorGeometry, doorMaterial);
    door.position.set(0, 5, houseDepth / 2 + 0.51);
    door.castShadow = true;
    door.receiveShadow = true;
    houseGroup.add(door);

    const doorKnob = new THREE.Mesh(new THREE.SphereGeometry(0.5, 16, 16), new THREE.MeshPhongMaterial({ color: 0xDAA520 }));
    doorKnob.position.set(2, 5, houseDepth / 2 + 1);
    houseGroup.add(doorKnob);

    // Roof with chimney
    const roofGeometry = new THREE.ConeGeometry(houseWidth / 1.5, 15, 4);
    const roofMaterial = new THREE.MeshPhongMaterial({ color: roofColor });
    const roof = new THREE.Mesh(roofGeometry, roofMaterial);
    roof.position.y = houseHeight + 10;
    roof.rotation.y = Math.PI / 4;
    roof.castShadow = true;
    roof.receiveShadow = true;
    houseGroup.add(roof);

    const chimneyGeometry = new THREE.BoxGeometry(5, 15, 5);
    const chimneyMaterial = new THREE.MeshPhongMaterial({ color: 0x8B4513 });
    const chimney = new THREE.Mesh(chimneyGeometry, chimneyMaterial);
    chimney.position.set(-houseWidth / 4, houseHeight + 15, 0);
    chimney.castShadow = true;
    chimney.receiveShadow = true;
    houseGroup.add(chimney);

    // Bounding Box for Walls
    const houseBox = new THREE.Box3().setFromObject(walls);
    houseGroup.userData.boundingBox = houseBox;

    houseGroup.castShadow = true;
    houseGroup.receiveShadow = true;

    return houseGroup;
}



export function createCar() {
    const carGroup = new THREE.Group();

    // Randomize car type
    const carTypes = ['sedan', 'pickup', 'sportsCar', 'limousine', 'bus', 'sportyCoupe'];
    const carType = carTypes[Math.floor(Math.random() * carTypes.length)];

    // Randomize car colors
    const carColors = [0xff0000, 0x0000ff, 0xffff00, 0x00ff00, 0xff00ff, 0xffa500, 0x8B0000, 0x00008B];
    const carColor = carColors[Math.floor(Math.random() * carColors.length)];
    const bodyMaterial = new THREE.MeshPhongMaterial({ color: carColor, shininess: 100 });

    let carWidth, carHeight, carLength;

    switch (carType) {
        case 'sedan':
            carWidth = 12;
            carHeight = 4;
            carLength = 28;
            break;
        case 'pickup':
            carWidth = 14;
            carHeight = 5;
            carLength = 32;
            break;
        case 'sportsCar':
            carWidth = 10;
            carHeight = 3;
            carLength = 26;
            break;
        case 'sportyCoupe':
            carWidth = 10;
            carHeight = 3.5;
            carLength = 24;
            break;
        case 'limousine':
            carWidth = 12;
            carHeight = 4;
            carLength = 50;
            break;
        case 'bus':
            carWidth = 16;
            carHeight = 8;
            carLength = 50;
            break;
    }

    // Body
    let body;
    if (carType === 'sportsCar' || carType === 'sportyCoupe') {
        // Rounded, aerodynamic body for sports cars
        const bodyGeometry = new THREE.BoxGeometry(carWidth, carHeight, carLength);
        body = new THREE.Mesh(bodyGeometry, bodyMaterial);
        body.rotation.z = Math.PI / 2;
        body.position.y = carHeight / 2 + 2;
    } else {
        // Standard boxy body for other cars
        const bodyGeometry = new THREE.BoxGeometry(carWidth, carHeight, carLength);
        body = new THREE.Mesh(bodyGeometry, bodyMaterial);
        body.position.y = carHeight / 2 + 2;
    }
    body.castShadow = true;
    body.receiveShadow = true;
    carGroup.add(body);

    // Additional features based on car type
    switch (carType) {
        case 'sportsCar':
        case 'sportyCoupe':
            // Spoiler
            const spoilerGeometry = new THREE.BoxGeometry(carWidth / 2, 0.5, 2);
            const spoilerMaterial = new THREE.MeshPhongMaterial({ color: carColor });
            const spoiler = new THREE.Mesh(spoilerGeometry, spoilerMaterial);
            spoiler.position.set(0, carHeight + 2.5, -carLength / 2 + 2);
            spoiler.castShadow = true;
            carGroup.add(spoiler);
            break;
        case 'pickup':
            // Cargo Bed
            const bedGeometry = new THREE.BoxGeometry(carWidth - 2, carHeight - 1, carLength / 2 - 2);
            const bedMaterial = new THREE.MeshPhongMaterial({ color: 0x654321 });
            const bed = new THREE.Mesh(bedGeometry, bedMaterial);
            bed.position.set(0, carHeight / 2 + 2, carLength / 4);
            bed.castShadow = true;
            carGroup.add(bed);
            break;
        case 'limousine':
            // Additional Windows
            const windowGeometry = new THREE.BoxGeometry(1, 2, carLength - 10);
            const windowMaterial = new THREE.MeshPhongMaterial({ color: 0x87CEEB, transparent: true, opacity: 0.6 });
            const windows = new THREE.Mesh(windowGeometry, windowMaterial);
            windows.position.set(0, carHeight + 2.5, 0);
            windows.castShadow = true;
            carGroup.add(windows);
            break;
    }

    // Cabin (skipped for sports cars to maintain rounded shape)
    if (carType !== 'sportsCar' && carType !== 'sportyCoupe') {
        const cabinGeometry = new THREE.BoxGeometry(carWidth - 2, carHeight + 2, carLength / 2);
        const cabinMaterial = new THREE.MeshPhongMaterial({ color: 0xcccccc, shininess: 100 });
        const cabin = new THREE.Mesh(cabinGeometry, cabinMaterial);
        cabin.position.set(0, carHeight + 3, -carLength / 4);
        cabin.castShadow = true;
        cabin.receiveShadow = true;
        carGroup.add(cabin);
    }

    // Headlights
    const headlightGeometry = new THREE.CircleGeometry(1, 16);
    const headlightMaterial = new THREE.MeshBasicMaterial({ color: 0xffffe0 });
    const headlight1 = new THREE.Mesh(headlightGeometry, headlightMaterial);
    headlight1.position.set(-carWidth / 2 + 1, carHeight / 2 + 2, carLength / 2 + 0.1);
    headlight1.rotation.x = -Math.PI / 2;
    carGroup.add(headlight1);

    const headlight2 = headlight1.clone();
    headlight2.position.x = carWidth / 2 - 1;
    carGroup.add(headlight2);

    // Taillights
    const taillightMaterial = new THREE.MeshBasicMaterial({ color: 0xff0000 });
    const taillight1 = new THREE.Mesh(headlightGeometry, taillightMaterial);
    taillight1.position.set(-carWidth / 2 + 1, carHeight / 2 + 2, -carLength / 2 - 0.1);
    taillight1.rotation.x = Math.PI / 2;
    carGroup.add(taillight1);

    const taillight2 = taillight1.clone();
    taillight2.position.x = carWidth / 2 - 1;
    carGroup.add(taillight2);

    // Wheels with rims
    const wheelGeometry = new THREE.CylinderGeometry(2, 2, 1, 16);
    const wheelMaterial = new THREE.MeshPhongMaterial({ color: 0x000000 });
    const rimGeometry = new THREE.CylinderGeometry(1.5, 1.5, 1.2, 16);
    const rimMaterial = new THREE.MeshPhongMaterial({ color: 0xC0C0C0 });

    const wheelPositions = [
        [-carWidth / 2 + 1.5, 2, -carLength / 2 + 5],
        [carWidth / 2 - 1.5, 2, -carLength / 2 + 5],
        [-carWidth / 2 + 1.5, 2, carLength / 2 - 5],
        [carWidth / 2 - 1.5, 2, carLength / 2 - 5]
    ];

    for (let i = 0; i < wheelPositions.length; i++) {
        const wheel = new THREE.Mesh(wheelGeometry, wheelMaterial);
        wheel.position.set(wheelPositions[i][0], wheelPositions[i][1], wheelPositions[i][2]);
        wheel.rotation.z = Math.PI / 2;
        wheel.castShadow = true;
        wheel.receiveShadow = true;
        carGroup.add(wheel);

        const rim = new THREE.Mesh(rimGeometry, rimMaterial);
        rim.position.copy(wheel.position);
        rim.rotation.copy(wheel.rotation);
        rim.castShadow = true;
        rim.receiveShadow = true;
        carGroup.add(rim);
    }

    // Bumpers (Adjust position for rounded cars)
    const bumperGeometry = new THREE.BoxGeometry(carWidth, 2, 2);
    const bumperMaterial = new THREE.MeshPhongMaterial({ color: 0x555555 });

    const frontBumper = new THREE.Mesh(bumperGeometry, bumperMaterial);
    frontBumper.position.set(0, 2.5, carLength / 2 + 1);
    frontBumper.castShadow = true;
    carGroup.add(frontBumper);

    const rearBumper = frontBumper.clone();
    rearBumper.position.z = -carLength / 2 - 1;
    carGroup.add(rearBumper);

    // Side Mirrors (Skip for sports cars)
    if (carType !== 'sportsCar' && carType !== 'sportyCoupe') {
        const mirrorGeometry = new THREE.BoxGeometry(0.5, 1, 2);
        const mirrorMaterial = new THREE.MeshPhongMaterial({ color: carColor });
        const mirror1 = new THREE.Mesh(mirrorGeometry, mirrorMaterial);
        mirror1.position.set(-carWidth / 2 - 0.5, carHeight + 1, -carLength / 4);
        carGroup.add(mirror1);

        const mirror2 = mirror1.clone();
        mirror2.position.x = carWidth / 2 + 0.5;
        carGroup.add(mirror2);
    }

    carGroup.castShadow = true;
    carGroup.receiveShadow = true;

    return carGroup;
}

