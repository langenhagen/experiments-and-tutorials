'use strict'


function DiscoDiscoScene() {
    
    var self = this;
    self.scene;
    self.camera;

    var cubemap;
    var discoball;
    
    // LOAD ASSETS // LOAD ASSETS // LOAD ASSETS // LOAD ASSETS // LOAD ASSETS // LOAD ASSETS // LOAD ASSETS // LOAD ASSETS // LOAD ASSETS
    this.loadAssetsAndCreateScene = function () {
        
        const cubeTextures = [
          './res/cubemap/posx.jpg',
          './res/cubemap/negx.jpg',
          './res/cubemap/posy.jpg',
          './res/cubemap/negy.jpg',
          './res/cubemap/posz.jpg',
          './res/cubemap/negz.jpg'
        ];

        cubemap = THREE.ImageUtils.loadTextureCube(cubeTextures);
         
        self.create();
        self.sceneReady = true;
    }

    // CREATE // CREATE // CREATE // CREATE // CREATE // CREATE // CREATE // CREATE // CREATE // CREATE // CREATE // CREATE // CREATE
    this.create = function() {
        
        self.scene = new THREE.Scene();
        self.camera = new THREE.PerspectiveCamera(
            60, 
            ENGINE.canvas.width / ENGINE.canvas.height, 
            10, 
            10000
        );

        var scene = self.scene;
        var cam = self.camera;

        
        cam.position.set(0, 0, -500);
        cam.lookAt( scene.position); 
        scene.add( cam);
        
        var light = new THREE.PointLight( 0xffffff );
        light.position.set( 400, -300, -500 );
        scene.add( light );
        
        
        // rope
        const thickness = 1.3, radiusSegments = 5, heightSegments = 1, height = 200;
        
        var ropeMaterial = new THREE.MeshPhongMaterial( { 
            color: 0x444444, emissive: 0x000000, specular: 0xAAAAAA, shininess: 20, 
            envMap: cubemap, reflectivity: 0.1, 
            shading: THREE.SmoothShading 
        });
        
        const ropeGeometry = new THREE.CylinderGeometry( 
            thickness,
            thickness,
            height,
            radiusSegments,
            heightSegments,
            true 
        );
        
        var rope = new THREE.Mesh( ropeGeometry, ropeMaterial);
        rope.position.set( 0.0, 200.0, 0.0);
        
        // ball
        const mirrorElements = 76;
        const radius = 100 , segments = mirrorElements*2, rings = mirrorElements;

        var ballMaterial = new THREE.MeshPhongMaterial({ 
            color: 0xFFFFFF, emissive: 0x111111, specular: 0xffffff, shininess: 0, 
            envMap: cubemap, reflectivity: 0.86,
            shading: THREE.FlatShading, blending: THREE.MultiplyBlending 
        });
        
        
        var ballGeometry = new THREE.SphereGeometry( radius, segments, rings);
        
        var ball = new THREE.Mesh( ballGeometry, ballMaterial );
        ball.position.set( 0.0, 50.0, 0.0);
        
        discoball = new THREE.Object3D();
        discoball.add( rope);
        discoball.add( ball);
        scene.add ( discoball );
        
    }

    // UPDATE // UPDATE // UPDATE // UPDATE // UPDATE // UPDATE // UPDATE // UPDATE // UPDATE // UPDATE // UPDATE // UPDATE // UPDATE
    this.update = function() {

        discoball.rotateY( 0.0001 * ENGINE.msSinceLastFrame);
        
        ENGINE.renderer.render( self.scene, self.camera);
    }
    
}