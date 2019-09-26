/**********************************************************
A verry simple linear blur with variable radius 
in one dimension.

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


uniform int radius; // must be positive
uniform float offsetPerPixel;
uniform bool horizontal;


void main(void) {

    vec4 sum = vec4(0,0,0,0);
    int divisor = 0;
    
    // revisited:
    // code could be more compact but writing the if-clause 
    // outside the for loop prevents from repeated checking.
    if( horizontal)
    {
        // horizontal
        for( int offset = -radius ; offset <= radius; ++offset)
        {
            int weight = radius - abs(offset) + 1;
            sum += weight * texture2D(texture, vertTexCoord.st + vec2( offset * offsetPerPixel, 0));
            divisor += weight;
        }
    }
    else
    {
        //vertical
        for( int offset = -radius ; offset <= radius; ++offset)
        {
            int weight = radius - abs(offset) + 1;
            sum += weight * texture2D(texture, vertTexCoord.st + vec2( 0, offset * offsetPerPixel));
            divisor += weight;
        }
    }
    
    gl_FragColor = sum / divisor;
}