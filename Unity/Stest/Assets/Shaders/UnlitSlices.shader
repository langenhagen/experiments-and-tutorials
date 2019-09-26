
Shader "Barn/UnlitSlices"
{
	Properties
	{        
        _speed          ("Speed ",       Float)             = 1
        _halfWidth      ("Half Width",   Range (0, 0.5))    = 0.1
        _offset         ("Offset",       Range (0, 1))      = 0
        
        [MaterialToggle] _transparent ("Set Black transparent", Float) = 0

        _direction      ("Direction 3D and Frequency",            Vector) = (0,1,0,0)
	}

	SubShader
	{
		Tags
		{ 
			"Queue"="Transparent" 
			"IgnoreProjector"="True" 
			"RenderType"="Transparent" 
			"PreviewType"="Plane"
			"CanUseSpriteAtlas"="True"
		}


		Lighting Off

		Fog { Mode Off }
		Blend SrcAlpha OneMinusSrcAlpha

		Pass
		{
		CGPROGRAM
			#pragma vertex   vertex_shader
			#pragma fragment fragment_shader
			#pragma multi_compile DUMMY PIXELSNAP_ON
			#include "UnityCG.cginc"
            
            
			struct vertex_input
			{
				float4 vertex   : POSITION;
				float4 color    : COLOR;
				float2 texcoord : TEXCOORD0;
			};

			struct fragment_input
			{
				float4 vertex   : SV_POSITION;
				fixed4 color    : COLOR;
				float2 texcoord : TEXCOORD0;
                float3 worldpos : TEXCOORD1;
            };
            
            
            float  _speed;
            float  _halfWidth;
            float  _frequency;
            float  _offset;
            float  _transparent;
            float4 _direction;
            
            
			fragment_input vertex_shader(vertex_input IN)
			{
				fragment_input OUT;
				OUT.vertex = mul(UNITY_MATRIX_MVP, IN.vertex);
				OUT.texcoord = IN.texcoord;
                OUT.color = IN.color;
				
                OUT.worldpos = mul (_Object2World, IN.vertex).xyz;
                
				return OUT;
			}
            			
            
			fixed4 fragment_shader(fragment_input IN) : COLOR
			{
                fixed4 ret;
                
                float time = fmod(_Time.y * _speed, 1);
                
                
                float val = abs ( fmod( dot(_direction.xyz, IN.worldpos) + time + _offset, 1));
                
                
                if( val < _halfWidth ||
                    val > 1 - _halfWidth)
                {
                    ret = float4( 1, 1, 1, 1);
                }
                else
                {
                    if( _transparent == 1)
                    {
                        ret = float4( 0, 0, 0, 0);
                    }
                    else
                    {
                        ret = float4( 0, 0, 0, 1);
                    }
                }
                
                return ret;
			}

            
		ENDCG
		}
	}
    
    

}
