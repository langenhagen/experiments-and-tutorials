/**********************************************************
version 140729

taken from:
http://lolengine.net/blog/2013/07/27/rgb-to-hsv-in-glsl

takes an hsva texture input el. ( [0..1], [0..1], [0..1], [0..1]) 
and converts it to rgba
**********************************************************/

#ifdef GL_ES
precision mediump float;
precision mediump int;
#endif

#define PROCESSING_TEXTURE_SHADER

uniform sampler2D texture;

varying vec4 vertTexCoord;

/* code is now inlined
vec3 hsv2rgb(vec3 c)
{
    vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);
    vec3 p = abs(fract(c.xxx + K.xyz) * 6.0 - K.www);
    return c.z * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), c.y);
}
*/


void main(void) {

    vec4 hsva_color = texture2D(texture, vertTexCoord.st);

	
	vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);
    vec3 p = abs(fract(hsva_color.xxx + K.xyz) * 6.0 - K.www);
	
    gl_FragColor = vec4( hsva_color.z * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), hsva_color.y) , hsva_color.a);
}