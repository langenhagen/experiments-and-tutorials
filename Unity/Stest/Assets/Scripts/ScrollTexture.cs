using UnityEngine;
using System.Collections;

public class ScrollTexture : MonoBehaviour
{
    public static float GlobalSpeed { get; set; }
    public static float GlobalAngle { get; set; }

    public bool     _doAngle = true;
    
    public float    _speed   = 0.1f;
    public float    _angle   = 0.0f;

    private float _off = 0;
    

    //##################################################################################################
    // METHODS

    void Start()
    {
        GlobalSpeed = 1;
        GlobalAngle = 0;
    }
	
	// Update is called once per frame
	void Update ()
    {

        // calculate offset

        _off += GlobalSpeed * _speed * Time.deltaTime;

        Vector2 offset;
        if (_doAngle)
            offset = new Vector2(Mathf.Sin(GlobalAngle) * _off, Mathf.Cos(Mathf.Deg2Rad * GlobalAngle + Mathf.Deg2Rad * _angle) * _off);
        else
            offset = new Vector2(0, _off);

        


        // assign offset to textures

        renderer.material.SetTextureOffset("_MainTex",      offset);
        
        if (renderer.material.HasProperty("_BumpMap"))
            renderer.material.SetTextureOffset("_BumpMap",  offset);
        if (renderer.material.HasProperty("_Illum"))
            renderer.material.SetTextureOffset("_Illum",    offset);
	}


    //##################################################################################################
    // GETTERS & SETTERS

    public float Speed  {   get { return _speed;    }
                            set { _speed = value;   }}

}
