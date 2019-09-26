var ENGINE = ENGINE || {};

// tweakables
ENGINE.printFPS = false;


// global vars
ENGINE.canvas;
ENGINE.renderer;

ENGINE.currentUpdateFunction;


ENGINE.frameCount = 0;
ENGINE.fps = 0;
ENGINE.timeStart = Date.now();
ENGINE.lastFrameTime = Date.now();
ENGINE.elapsedTime = 0;
ENGINE.msSinceLastFrame = 0;


ENGINE.lastMouseX = 0;
ENGINE.lastMouseY = 0;
ENGINE.mouseX = 0;
ENGINE.mouseY = 0;
ENGINE.mouseOffsetX = 0;
ENGINE.mouseOffsetY = 0;


/**
*/
ENGINE.initAndStartWebGLApp = function initAndStartWebGLApp() {
    if( !ENGINE.initWebGLOrShowErrorMsg())
        return;
    
    // Set the initial update function here.
    ENGINE.currentUpdateFunction = SceneList[0].update;
    
    for( var i=0; i<SceneList.length; ++i) {
        setTimeout( SceneList[i].loadAssetsAndCreateScene() , 0);
    }
    
    window.addEventListener(
        'resize',
        function( evt) {
            
            const newAspect = window.innerWidth / window.innerHeight;
            
            for( var i=0; i<SceneList.length; ++i) {
                
                var cam = SceneList[i].camera;
                
                cam.aspect = newAspect;
                cam.updateProjectionMatrix();
            }
            
            ENGINE.renderer.setSize( window.innerWidth, window.innerHeight );
        },
        false
    );
    
    // blocking wait until first scene is ready to be shown
    while( !SceneList[0].sceneReady) {
        ;
    }
    
    
    ENGINE.frameLoop();
}


/**
*/
ENGINE.initWebGLOrShowErrorMsg = function() {
    
    if ( !Detector.webgl) {

        var statusDiv = $("#status");
        
        const msg = "Your Browser does not support OpenGL.";
        
        statusDiv.text( msg);
        
        console.log( msg);
        
        return false;
    }

    // *** WebGL is supported ***

    document.onmousemove = ENGINE.updateMousePos; // TODO

    this.canvas = document.getElementById("gl");
    this.canvas.width = window.innerWidth;
    this.canvas.height = window.innerHeight;
    
    this.renderer = new THREE.WebGLRenderer( { canvas: this.canvas, antialias: true });
    this.renderer.setClearColor(0x000000, 1);
    
    return true;
}


/**
*/
ENGINE.frameLoop = function() {
    this.elapsedTime = Date.now() - this.timeStart;
    this.msSinceLastFrame = Date.now() - this.lastFrameTime;
    
    this.mouseOffsetX = this.lastMouseX - this.mouseX;
    this.mouseOffsetY = this.lastMouseY - this.mouseY;
    this.lastMouseX = this.mouseX;
    this.lastMouseY = this.mouseY;
    
    
    ENGINE.currentUpdateFunction();
    
    requestAnimationFrame( function() {
        ENGINE.frameLoop();
    });
    
    
    if( this.printFPS) {
        this.fps = 1000.0 / (Date.now() - this.lastFrameTime);
        console.log( this.fps); /**/
    }

    
    /*
    this.frameCount++;
    console.log(this.frameCount);
    /**/ 

    this.lastFrameTime = Date.now();
}


/**
*/
ENGINE.updateMousePos = function(e) {
    ENGINE.mouseX = Math.max(0, e.pageX);
    ENGINE.mouseY = Math.max(0, e.pageY);
}


/**
*/
ENGINE.createMarker = function( scene, x, y, z, size, color) {
    
    if (typeof(size)==='undefined') size = 1;
    if (typeof(color)==='undefined') color = 0xff0000;
    
    var geometry = new THREE.BoxGeometry( size, size, size );
    var material = new THREE.MeshBasicMaterial( {color: color} );
    var cube = new THREE.Mesh( geometry, material );
    cube.position.set( x, y, z);
    scene.add( cube );
}


/**
*/
ENGINE.createMarker2 = function( scene, position, size, color) {
    ENGINE.createMarker( scene, position.x, position.y, position.z, size, color);
}