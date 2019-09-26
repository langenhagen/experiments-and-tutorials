Shader "Barn/Mandelbrot" {

    Properties
	{

        _coordX ("Coord X", Float) = 0.0
        _coordY ("Coord Y", Float) = 0.0
        _colorQuantize ("Color Quantize", Float) = 29.0

    }
	SubShader {
		Pass {
			CGPROGRAM
			#pragma vertex vert_img
			#pragma fragment frag
			#pragma target 3.0

			#include "UnityCG.cginc"

            float _coordX;
            float _coordY;
            float _colorQuantize;

            
			float4 frag(v2f_img i) : COLOR {
				float2 mcoord;
				float2 coord = float2(_coordX,_coordY);
				mcoord.x = ((1.0-i.uv.x)*3.5)-2.5;
				mcoord.y = (i.uv.y*2.0)-1.0;
				float iteration = 0.0;
				const float _MaxIter = 29.0;
				const float PI = 3.14159;
				float xtemp;
				for ( iteration = 0.0; iteration < _MaxIter; iteration += 1.0) {
					if ( coord.x*coord.x + coord.y*coord.y > 2.0*(cos(fmod(_Time.y,2.0*PI))+1.0) )
                        break;

					xtemp = (coord.x*coord.x - coord.y*coord.y + mcoord.x);
					coord.y = (2.0*coord.x*coord.y + mcoord.y);
					coord.x = xtemp;
				}
				float val = fmod(iteration/_colorQuantize+_Time.x,1.0);
				float4 color;

				color.r = clamp((3.0*abs(fmod(2.0*val,1.0)-0.5)),0.0,1.0);
				color.g = clamp((3.0*abs(fmod(2.0*val+(1.0/3.0),1.0)-0.5)),0.0,1.0);
				color.b = color.r;//clamp((3.0*abs(fmod(2.0*val-(1.0/3.0),1.0)-0.5)),0.0,1.0);
				color.a = 1.0;

				return color;
			}
			ENDCG
		}
	}
}