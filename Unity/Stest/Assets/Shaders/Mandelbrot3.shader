Shader "Barn/Mandelbrot3" {

	Properties {
		MaxIterations ("Mandelbrot iteration depth", Float) = 5
		Zoom ("Mandelbrot Zoom", Float) = 1
//		Center ("mandelbrot Center position (XY)", Vector) = (0.5,0.5,0,0)
		InnerColor ("Color 1 (inner)", Color) = (1,0,0,1)
		OuterColor1 ("Color 1 (outer)", Color) = (0,1,0,0)
		OuterColor2 ("Color 2 (outer)", Color) = (0,0,1,0.3)
		
		Creal ("Mandelbrot Step", Float) = -0.0002
		Cimag ("Mandelbrot Shape", Float) = 0.7383
	}
    
	SubShader {
        
        Tags
		{ 
			"Queue"="Transparent" 
			"RenderType"="Transparent" 
			"PreviewType"="Plane"
        }
        
    
        Blend SrcAlpha OneMinusSrcAlpha
		Pass {
        
            Cull Off
        
			CGPROGRAM
			#pragma glsl
			#pragma vertex vert_img
			#pragma fragment frag
			#pragma target 3.0

			#include "UnityCG.cginc"
			
			uniform float MaxIterations;
			uniform float Zoom;
			uniform float4 Center;
			uniform float4 InnerColor;
			uniform float4 OuterColor1;
			uniform float4 OuterColor2;
			uniform float Creal;
			uniform float Cimag;

			float4 frag(v2f_img i) : COLOR {
				float real = i.uv.x * Zoom + -(Zoom/2.0);
				float imag = i.uv.y * Zoom + -(Zoom/2.0);
				
				
				float r2 = 0.0;
				float iter = 0.0;
				
				for (iter = 0.0; iter < MaxIterations && r2 < 4.0; iter += 1.0)
				{					
					float tempreal = real;
	
					real = (tempreal * tempreal) - (imag * imag) + Creal;
					imag = 2.0 * tempreal * imag + Cimag;
					r2   = (real * real) + (imag * imag);
				}
				
				float4 color;
				float4 alpha = (iter * 0.05) - floor(iter * 0.05);
				
				if (r2 < 4.0)
				    color = InnerColor;
				else
				    color = OuterColor1*(1.0 - alpha) + OuterColor2*alpha; // linearly interpolate color
    
				return color;
			}
			ENDCG
		}
	}
}