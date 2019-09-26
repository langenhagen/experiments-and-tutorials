#define PROCESSING_COLOR_SHADER

#ifdef GL_ES
precision mediump float;
precision mediump int;
#endif

uniform sampler2D texture;

varying vec4 vertTexCoord;
varying float noiseVal;

void main() {

    vec3 color = vec3( vertTexCoord.st * ( 1.0 - 2.0 * noiseVal ), 0.0 );
    gl_FragColor = vec4( color.rgb, 1.0 );
}
