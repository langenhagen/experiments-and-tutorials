Shader "Barn/Mandelbrot2" {

    Properties
	{

        _coordX ("Coord X", Float) = 0.0
        _coordY ("Coord Y", Float) = 0.0
        _colorQuantize ("Color Quantize", Float) = 29.0

    }
    
	SubShader 
    {
		Pass
        {
			CGPROGRAM
			#pragma vertex vert_img
			#pragma fragment Julia_PixelShader

            #include "UnityCG.cginc"
        
            int Iterations = 128;
            float2 Pan = float2(0.5, 0);
            float Zoom = 3;
            float Aspect = 1;
            float2 JuliaSeed = float2(0.39, -0.2);
            float3 ColorScale = float3(4, 5, 6);

            float ComputeValue(float2 v, float2 offset)
            {
            float vxsquare = 0;
            float vysquare = 0;

            int iteration = 0;
            int lastIteration = Iterations;

            do
            {
            vxsquare = v.x * v.x;
            vysquare = v.y * v.y;

            v = float2(vxsquare - vysquare, v.x * v.y * 2) + offset;

            iteration++;

            if ((lastIteration == Iterations) && (vxsquare + vysquare) > 4.0)
            {
            lastIteration = iteration + 1;
            }
            }
            while (iteration < lastIteration);

            return (float(iteration) - (log(log(sqrt(vxsquare + vysquare))) / log(2.0))) / float(Iterations);
            }

            float4 Mandelbrot_PixelShader(float2 texCoord : TEXCOORD0) : COLOR0
            {
            float2 v = (texCoord - 0.5) * Zoom * float2(1, Aspect) - Pan;

            float val = ComputeValue(v, v);

            return float4(sin(val * ColorScale.x), sin(val * ColorScale.y), sin(val * ColorScale.z), 1);
            }

            float4 Julia_PixelShader(float2 texCoord : TEXCOORD0) : COLOR0
            {
            float2 v = (texCoord - 0.5) * Zoom * float2(1, Aspect) - Pan;

            float val = ComputeValue(v, JuliaSeed);

            return float4(sin(val * ColorScale.x), sin(val * ColorScale.y), sin(val * ColorScale.z), 1);
            }
            
            ENDCG
        }
    }
}