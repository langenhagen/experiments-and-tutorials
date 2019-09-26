#ifdef GL_ES
precision mediump float;
precision mediump int;
#endif

#define PROCESSING_TEXTURE_SHADER

#define SQUARED_RADIUS 9
#define RADIUS 3

varying vec4 vertTexCoord;
varying vec3 arr[ SQUARED_RADIUS];

uniform sampler2D texture;

/*
void bubbleSort()
{
    bool swapped = true;
    int j = 0;
    float tmp;
    for (int c = 0; c < RADIUS; c--)
    {
        if (!swapped)
            break;
        swapped = false;
        j++;
        for (int i = 0; i < RADIUS; i++)
        {
            if (i >= RADIUS - j)
                break;
            if (arr[i] > arr[i + 1])
            {
                tmp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = tmp;
                swapped = true;
            }
        }
    }
}
*/

void bubbleSortVec3()
{
    bool swapped = true;
    int j = 0;
    vec3 tmp;
    for (int c = 0; c < RADIUS; c--)
    {
        if (!swapped)
            break;
        swapped = false;
        j++;
        for (int i = 0; i < RADIUS; i++)
        {
            if (i >= RADIUS - j)
                break;
            if ( dot(arr[i], arr[i]) > dot(arr[i + 1],arr[i + 1]))
            {
                tmp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = tmp;
                swapped = true;
            }
        }
    }
}


void main() {
    
    ivec2 texSize = textureSize2D( texture,0);
    ivec2 invTexSize = ivec2(1,1) / texSize;
    
    // fill array
    int counter = 0;
    for( int r = 0; r < RADIUS; ++r)
    {
        for( int c = 0; c < RADIUS; ++c)
        {
            arr[counter++] = texture2D(texture, vertTexCoord.st).rgb;
        }
    }
    
    // sort array
    bubbleSortVec3();
    
    gl_FragColor = vec4( arr[5],1);
    
}

