using UnityEngine;
using System.Collections;

public class RotateRandom : MonoBehaviour {

    public KeyCode TriggerKeyCode = KeyCode.LeftControl;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
         
        if (Input.GetKeyDown(TriggerKeyCode))
        {
            Vector3 eulerAngles = new Vector3(Random.Range(0, 360), Random.Range(0, 360), Random.Range(0, 360));
            
            transform.Rotate(eulerAngles);

        }
	
	}
}
