'use strict'


function TemplateScene() {
    
    var self = this;
    self.scene;
    self.camera;

    
    // LOAD ASSETS // LOAD ASSETS // LOAD ASSETS // LOAD ASSETS // LOAD ASSETS // LOAD ASSETS // LOAD ASSETS // LOAD ASSETS // LOAD ASSETS
    this.loadAssetsAndCreateScene = function () {
        
        // Load assets here ...
         
        // call these after loading
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
        
        
        // IMPLEMENT ...
        
    }

    // UPDATE // UPDATE // UPDATE // UPDATE // UPDATE // UPDATE // UPDATE // UPDATE // UPDATE // UPDATE // UPDATE // UPDATE // UPDATE
    this.update = function() {

        // IMPLEMENT ...
    
        ENGINE.renderer.render( self.scene, self.camera);
    }
}