Shader "Barn/WhiteStripes"
{
	Properties
	{
		_MainTex ("Texture", 2D) = "white" {}
        
        _speed          ("Speed ",                  Float)          = 1
        _width          ("Width",                   Range (0, 1))   = 0.1
        _frequency      ("Repetition Frequency",    Float)          = 1
        _offset         ("Offset",                  Range (0, 1))   = 0
        
        [MaterialToggle] _transparent ("Set Black transparent", Float) = 0
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

		Cull Off
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
				fixed4 color    : COLOR;
				float2 texcoord : TEXCOORD0;
			};

			struct fragment_input
			{
				float4 vertex   : SV_POSITION;
				fixed4 color    : COLOR;
				float2 texcoord : TEXCOORD0;
            };
            
            
            sampler2D _MainTex;
            
            float _speed;
            float _width;
            float _frequency;
            fixed _offset;
            fixed _transparent;
                 
			fragment_input vertex_shader(vertex_input IN)
			{
				fragment_input OUT;
				OUT.vertex = mul(UNITY_MATRIX_MVP, IN.vertex);
				OUT.texcoord = IN.texcoord;
                OUT.color = IN.color;
				
                #ifdef PIXELSNAP_ON
				OUT.vertex = UnityPixelSnap (OUT.vertex);
				#endif
                
				return OUT;
			}
            			
            
			fixed4 fragment_shader(fragment_input IN) : COLOR
			{
                fixed4 ret = tex2D(_MainTex, IN.texcoord);
                
                float width = _width * _frequency;
                
                float time = fmod(_Time.y * _speed * _frequency, _frequency);
                
                float val = fmod( ret.r + time + _offset, _frequency);
                
                if( val < width &&
                    ret.r > 0)
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
