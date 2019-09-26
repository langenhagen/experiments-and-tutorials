/**********************************************************
A verry simple and slow blur with variable radius.

author: barn
version 140602

**********************************************************/

#ifdef GL_ES
precision mediump float;
precision mediump int;
#endif

#define PROCESSING_TEXTURE_SHADER

uniform sampler2D texture;

varying vec4 vertTexCoord;

uniform int filterRadius;
uniform float offsetPerPixelX;
uniform float offsetPerPixelY;


void main(void) {

    vec4 sum = vec4(0,0,0,0);
    
    for( int colOffset = -filterRadius ; colOffset <= filterRadius; ++colOffset)
    {
        for( int rowOffset = -filterRadius ; rowOffset <= filterRadius; ++rowOffset)
        {
            sum += texture2D(texture, vertTexCoord.st + vec2( colOffset * offsetPerPixelX, rowOffset * offsetPerPixelY));
        }
    }
    
    gl_FragColor = sum / ( (filterRadius * 2 + 1)*(filterRadius * 2 + 1) );
}
