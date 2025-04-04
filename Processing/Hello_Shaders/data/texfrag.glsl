#ifdef GL_ES
precision mediump float;
precision mediump int;
#endif


#define TAU 6.28318530718

uniform sampler2D texture;

varying vec4 vertColor;
varying vec4 vertTexCoord;

void main() {
    
    gl_FragColor = texture2D(texture, vertTexCoord.st) * vertColor;
}
