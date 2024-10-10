// main.js
import * as THREE from 'three';
import { OrbitControls } from 'OrbitControls';
import { STLExporter } from 'STLExporter';

// Detail level for geometries
// const detailLevel = 32; // Default for rendering in Katzespiel is 32
const detailLevel = 128; // Increase this value for smoother geometries


function init() {
    // Scene
    const scene = new THREE.Scene();

    // Renderer
    const renderer = new THREE.WebGLRenderer({ antialias: true });
    renderer.setSize(window.innerWidth, window.innerHeight);
    renderer.shadowMap.enabled = true;
    document.body.appendChild(renderer.domElement);

    // Camera
    const camera = new THREE.PerspectiveCamera(50, window.innerWidth / window.innerHeight, 0.1, 1000);
    camera.position.set(0, 6, 20);

    // Controls
    const controls = new OrbitControls(camera, renderer.domElement);
    controls.enableDamping = true;

    // Light
    const ambientLight = new THREE.AmbientLight(0x404040, 1.5);
    scene.add(ambientLight);

    const directionalLight = new THREE.DirectionalLight(0xffffff, 1);
    directionalLight.position.set(10, 20, 10);
    directionalLight.castShadow = true;
    scene.add(directionalLight);


    /**/
    // Create Cat for Export (without whiskers)
    const cat = createCat(0x888888, false);
    cat.position.y -= 3;
    scene.add(cat);
    /**/

    /*
    // Create Cat with Whiskers for Display (optional)
    const catWithWhiskers = createCat(0x888888, true);
    catWithWhiskers.position.y -= 3;
    scene.add(catWithWhiskers);
    /**/

    // Export Button
    const exportButton = document.getElementById('exportButton');
    exportButton.addEventListener('click', () => {
        exportToSTL(cat);
    });

    // Resize Handler
    window.addEventListener('resize', onWindowResize);

    function onWindowResize() {
        camera.aspect = window.innerWidth / window.innerHeight;
        camera.updateProjectionMatrix();

        renderer.setSize(window.innerWidth, window.innerHeight);
    }

    // Animation Loop
    function animate() {
        requestAnimationFrame(animate);

        controls.update();
        renderer.render(scene, camera);
    }

    animate();
}

function createCat(color, includeWhiskers = true) {
    const cat = new THREE.Group();

    // Body
    const bodyGeometry = new THREE.SphereGeometry(2, detailLevel, detailLevel);
    const bodyMaterial = new THREE.MeshPhongMaterial({ color: color, shininess: 30 });
    const body = new THREE.Mesh(bodyGeometry, bodyMaterial);
    body.position.y = 3;
    body.castShadow = true;
    cat.add(body);
    cat.userData.body = body; // Store reference for animation

    // Head
    const headGeometry = new THREE.SphereGeometry(1.5, detailLevel, detailLevel);
    const headMaterial = new THREE.MeshPhongMaterial({ color: color, shininess: 30 });
    const head = new THREE.Mesh(headGeometry, headMaterial);
    head.position.set(0, 5, 1);
    head.castShadow = true;
    cat.add(head);

    // Ears
    const earGeometry = new THREE.ConeGeometry(0.5, 1, detailLevel);
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
    const eyeGeometry = new THREE.SphereGeometry(0.2, detailLevel, detailLevel);
    const eyeWhiteMaterial = new THREE.MeshPhongMaterial({ color: 0xffffff, shininess: 30 });
    const eye1 = new THREE.Mesh(eyeGeometry, eyeWhiteMaterial);
    eye1.position.set(-0.4, 5.3, 2.2);
    cat.add(eye1);

    const eye2 = eye1.clone();
    eye2.position.x = 0.4;
    cat.add(eye2);

    // Pupils
    const pupilGeometry = new THREE.SphereGeometry(0.1, detailLevel, detailLevel);
    const pupilMaterial = new THREE.MeshPhongMaterial({ color: 0x000000, shininess: 30 });
    const pupil1 = new THREE.Mesh(pupilGeometry, pupilMaterial);
    pupil1.position.set(-0.4, 5.3, 2.4);
    cat.add(pupil1);

    const pupil2 = pupil1.clone();
    pupil2.position.x = 0.4;
    cat.add(pupil2);

    // Whiskers
    if (includeWhiskers) {
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
    }

    // Legs
    const legGeometry = new THREE.CylinderGeometry(0.3, 0.3, 2, detailLevel);
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
    const tailGeometry = new THREE.CylinderGeometry(0.2, 0.2, 3, detailLevel);
    const tailMaterial = new THREE.MeshPhongMaterial({ color: color, shininess: 30 });
    const tail = new THREE.Mesh(tailGeometry, tailMaterial);
    tail.position.set(0, 3, -1.5);
    tail.rotation.x = Math.PI / 4;
    tail.castShadow = true;
    cat.add(tail);

    // Stripes (for more detail)
    const stripeGeometry = new THREE.CylinderGeometry(0.25, 0.25, 0.5, detailLevel);
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

function exportToSTL(object) {
    const exporter = new STLExporter();
    const result = exporter.parse(object);

    // Create a blob and download the STL file
    const blob = new Blob([result], { type: 'application/octet-stream' });
    const link = document.createElement('a');
    link.style.display = 'none';
    document.body.appendChild(link);

    link.href = URL.createObjectURL(blob);
    link.download = 'cat_model.stl';
    link.click();

    // Cleanup
    document.body.removeChild(link);
}

init();

