/**********************************************************
A verry simple (u cannot even call it) dithering shader
that assigns black or white to a pixel according to the
brightness of a pixel.

author: barn
version 140727

**********************************************************/

#ifdef GL_ES
precision mediump float;
precision mediump int;
#endif

#define PROCESSING_TEXTURE_SHADER

uniform sampler2D texture;

varying vec4 vertTexCoord;

// random - u can use it - but you dont have to
uniform float random; 
uniform float offset;


// Some weirdo pseudo-random function found on the internet.
// Hey, it kinda works =D !
float rand(vec2 v){
    return fract(sin(dot(v.xy ,vec2(12.9898,78.233))) * 43758.5453);
}


void main() {
    
    vec3 texColor = texture2D(texture, vertTexCoord.st).rgb;
    
    if( dot( texColor, texColor) * 0.33333 + offset > rand(vertTexCoord.st + random))
        gl_FragColor = vec4(1,1,1,1);
    else
        gl_FragColor = vec4(0,0,0,1);
    
}

