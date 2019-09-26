using UnityEngine;
using System.Collections;

public class Mandelbrodel : MonoBehaviour {

	// Use this for initialization
	void Start ()
    {
        AdjustMandelbrot();
	
	}
	
	// Update is called once per frame
	void Update () {

        AdjustMandelbrot();
	}

    public void AdjustMandelbrot()
    {
        if (renderer.material.HasProperty("Creal"))
            renderer.material.SetFloat("Creal", transform.position.z);
    }
}
