// scripts/player.js
import * as THREE from 'three';

export function createPlayer() {
    const player = createCat(0x888888); // Gray color
    player.position.y = 0; // Adjusted to stand on ground
    return player;
}

export function createCat(color) {
    const cat = new THREE.Group();

    // Body
    const bodyGeometry = new THREE.SphereGeometry(2, 32, 32);
    const bodyMaterial = new THREE.MeshPhongMaterial({ color: color, shininess: 30 });
    const body = new THREE.Mesh(bodyGeometry, bodyMaterial);
    body.position.y = 3;
    body.castShadow = true;
    cat.add(body);
    cat.userData.body = body; // Store reference for animation

    // Head
    const headGeometry = new THREE.SphereGeometry(1.5, 32, 32);
    const headMaterial = new THREE.MeshPhongMaterial({ color: color, shininess: 30 });
    const head = new THREE.Mesh(headGeometry, headMaterial);
    head.position.set(0, 5, 1);
    head.castShadow = true;
    cat.add(head);

    // Ears
    const earGeometry = new THREE.ConeGeometry(0.5, 1, 32);
    const earMaterial = new THREE.MeshPhongMaterial({ color: color, shininess: 30 });
    const ear1 = new THREE.Mesh(earGeometry, earMaterial);
    ear1.position.set(-0.7, 6.5, 1);
    ear1.rotation.z = Math.PI / 8;
    ear1.castShadow = true;
    cat.add(ear1);

    const ear2 = ear1.clone();
    ear2.position.x = 0.7;
    ear2.rotation.z = -Math.PI / 8;
    cat.add(ear2);

    // Eyes with Pupils
    const eyeGeometry = new THREE.SphereGeometry(0.2, 16, 16);
    const eyeWhiteMaterial = new THREE.MeshPhongMaterial({ color: 0xffffff, shininess: 30 });
    const eye1 = new THREE.Mesh(eyeGeometry, eyeWhiteMaterial);
    eye1.position.set(-0.4, 5.3, 2.2);
    cat.add(eye1);

    const eye2 = eye1.clone();
    eye2.position.x = 0.4;
    cat.add(eye2);

    // Pupils
    const pupilGeometry = new THREE.SphereGeometry(0.1, 16, 16);
    const pupilMaterial = new THREE.MeshPhongMaterial({ color: 0x000000, shininess: 30 });
    const pupil1 = new THREE.Mesh(pupilGeometry, pupilMaterial);
    pupil1.position.set(-0.4, 5.3, 2.4);
    cat.add(pupil1);

    const pupil2 = pupil1.clone();
    pupil2.position.x = 0.4;
    cat.add(pupil2);

    // Whiskers
    const whiskerMaterial = new THREE.LineBasicMaterial({ color: 0x000000 });
    const whiskerPointsLeft = [
        new THREE.Vector3(-0.2, 5, 2.5),
        new THREE.Vector3(-1.0, 5, 2.5)
    ];
    const whiskerGeometryLeft = new THREE.BufferGeometry().setFromPoints(whiskerPointsLeft);
    const whiskerLeft = new THREE.Line(whiskerGeometryLeft, whiskerMaterial);
    cat.add(whiskerLeft);

    const whiskerPointsRight = [
        new THREE.Vector3(0.2, 5, 2.5),
        new THREE.Vector3(1.0, 5, 2.5)
    ];
    const whiskerGeometryRight = new THREE.BufferGeometry().setFromPoints(whiskerPointsRight);
    const whiskerRight = new THREE.Line(whiskerGeometryRight, whiskerMaterial);
    cat.add(whiskerRight);

    // Legs
    const legGeometry = new THREE.CylinderGeometry(0.3, 0.3, 2, 16);
    const legMaterial = new THREE.MeshPhongMaterial({ color: color, shininess: 30 });
    const legPositions = [
        [-0.8, 1, -0.5],
        [0.8, 1, -0.5],
        [-0.8, 1, 0.5],
        [0.8, 1, 0.5]
    ];
    cat.userData.legs = [];
    for (let i = 0; i < legPositions.length; i++) {
        const leg = new THREE.Mesh(legGeometry, legMaterial);
        leg.position.set(legPositions[i][0], legPositions[i][1], legPositions[i][2]);
        leg.castShadow = true;
        cat.add(leg);
        cat.userData.legs.push(leg);
    }

    // Tail
    const tailGeometry = new THREE.CylinderGeometry(0.2, 0.2, 3, 16);
    const tailMaterial = new THREE.MeshPhongMaterial({ color: color, shininess: 30 });
    const tail = new THREE.Mesh(tailGeometry, tailMaterial);
    tail.position.set(0, 3, -1.5);
    tail.rotation.x = Math.PI / 4;
    tail.castShadow = true;
    cat.add(tail);

    // Stripes (for more detail)
    const stripeGeometry = new THREE.CylinderGeometry(0.25, 0.25, 0.5, 16);
    const stripeMaterial = new THREE.MeshPhongMaterial({ color: 0x000000, shininess: 30 });
    for (let i = -1; i <= 1; i++) {
        const stripe = new THREE.Mesh(stripeGeometry, stripeMaterial);
        stripe.position.set(i * 0.7, 3 + i, 0);
        stripe.rotation.z = Math.PI / 2;
        stripe.castShadow = true;
        cat.add(stripe);
    }

    cat.castShadow = true;
    cat.receiveShadow = true;

    cat.userData.walkTime = 0;
    cat.userData.sitProgress = 0; // Initialize sit progress

    return cat;
}

export function animateLegs(cat, delta) {
    const speed = 10;
    const angle = Math.sin(cat.userData.walkTime * speed) * 0.5;

    if (cat.userData.legs && cat.userData.legs.length === 4) {
        cat.userData.legs[0].rotation.x = angle;
        cat.userData.legs[1].rotation.x = -angle;
        cat.userData.legs[2].rotation.x = -angle;
        cat.userData.legs[3].rotation.x = angle;
    }
}

export function resetLegs(cat) {
    if (cat.userData.legs && cat.userData.legs.length === 4) {
        cat.userData.legs.forEach(leg => {
            leg.rotation.x = 0;
        });
    }
}

export function animateSitting(cat, delta, isSitting) {
    // Animate between standing and sitting positions
    const sitDuration = 0.5; // Duration to complete the sitting/standing animation
    const sitSpeed = delta / sitDuration;

    if (isSitting) {
        // Animate to sitting position
        cat.userData.sitProgress = Math.min((cat.userData.sitProgress || 0) + sitSpeed, 1);

        // Lower the body
        cat.position.y = THREE.MathUtils.lerp(-0.5, -2, cat.userData.sitProgress);

    } else {
        // Animate to standing position
        cat.userData.sitProgress = Math.max((cat.userData.sitProgress || 0) - 3*sitSpeed, 0);

        // Raise the body
        cat.position.y = THREE.MathUtils.lerp(-0.5, -2, cat.userData.sitProgress);
    }
}
