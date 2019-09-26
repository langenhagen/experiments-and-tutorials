/**********************************************************
version 140729

takes an rgba texture input and converts
it to hsva el. ( [0..1], [0..1], [0..1], [0..1])

using algorithm found at
http://lolengine.net/blog/2013/07/27/rgb-to-hsv-in-glsl

commented out:
algorithm taken from wikipedia and a little bit
improvised for glsl implementation.
**********************************************************/

#ifdef GL_ES
precision mediump float;
precision mediump int;
#endif

#define PROCESSING_TEXTURE_SHADER

uniform sampler2D texture;

varying vec4 vertTexCoord;


/* code is now inlined
vec3 rgb2hsv(vec3 c)
{
    vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);
    vec4 p = mix(vec4(c.bg, K.wz), vec4(c.gb, K.xy), step(c.b, c.g));
    vec4 q = mix(vec4(p.xyw, c.r), vec4(c.r, p.yzx), step(p.x, c.r));

    float d = q.x - min(q.w, q.y);
    float e = 1.0e-10;
    return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x);
}
*/

void main(void) {

    vec4 rgba_color = texture2D(texture, vertTexCoord.st);
    
	
	vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);
    vec4 p = mix(vec4(rgba_color.bg, K.wz), vec4(rgba_color.gb, K.xy), step(rgba_color.b, rgba_color.g));
    vec4 q = mix(vec4(p.xyw, rgba_color.r), vec4(rgba_color.r, p.yzx), step(p.x, rgba_color.r));

    float d = q.x - min(q.w, q.y);
    float e = 1.0e-10;
	
    gl_FragColor = vec4( vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x), rgba_color.a);
}