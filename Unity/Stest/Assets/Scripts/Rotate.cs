using UnityEngine;
using System.Collections;

public class Rotate : MonoBehaviour {


    public Vector3 _axis = Vector3.up;

    public float _speed = 1;


	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {

        transform.Rotate(_axis, _speed * Time.deltaTime);
	}
}
