/**********************************************************
version 140729

quantizes xyz values of an image.
feel free to adjust the uniform multiplier variable 
at runtime!
Doesn't quantize the alpha channel.

**********************************************************/

#ifdef GL_ES
precision mediump float;
precision mediump int;
#endif

#define PROCESSING_TEXTURE_SHADER

uniform sampler2D texture;

varying vec4 vertTexCoord;


uniform int multiplier;

#define quantize1(val, multiplier)    round(val * multiplier) / multiplier



void main(void) {

    vec4 tex_color = texture2D(texture, vertTexCoord.st);
    
    gl_FragColor = vec4( quantize1(tex_color.r, multiplier), quantize1(tex_color.g, multiplier), quantize1(tex_color.b, multiplier), tex_color.a);
}