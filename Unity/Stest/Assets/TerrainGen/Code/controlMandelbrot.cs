using UnityEngine;
using System.Collections;

public class controlMandelbrot : MonoBehaviour {
	
	public Material controlMat;
	
	public float maxStep = 1f, maxShape = 3f;
	private float curStep, curShape;	
	
	public float speed = 1f;
	
	void Start() {
		curStep = controlMat.GetFloat("Creal");
		curShape = controlMat.GetFloat("Cimag");
	}
	
	// Update is called once per frame
	void Update () {
		curStep = Mathf.Sin(Time.time * speed) * maxStep;
		curShape = Mathf.Sin(Time.time * speed) * maxShape;
		
		controlMat.SetFloat("Creal", curStep);
		controlMat.SetFloat("Cimag", curShape);
	}
}
