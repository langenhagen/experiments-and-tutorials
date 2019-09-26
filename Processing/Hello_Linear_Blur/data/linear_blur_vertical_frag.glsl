/**********************************************************
A verry simple vertical linear blur with variable radius 
in one dimension. It is slightly faster than the generic
variant where u must specify the blur direction.

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


void main(void) {

    vec4 sum = vec4(0,0,0,0);
    int divisor = 0;

	for( int offset = -radius ; offset <= radius; ++offset)
	{
		int weight = radius - abs(offset) + 1;
		sum += weight * texture2D(texture, vertTexCoord.st + vec2( 0, offset * offsetPerPixel));
		divisor += weight;
	}
    
    gl_FragColor = sum / divisor;
}