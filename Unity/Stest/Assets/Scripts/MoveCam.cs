using UnityEngine;
using System.Collections;

public class MoveCam : MonoBehaviour {

    public float ForwardSpeed   = 4;
    public float SideSpeed      = 4;
    public float BackSpeed      = 4;
    public float UpSpeed        = 4;
    public float DownSpeed      = 4;
    public float XRotSpeed      = 30;
    public float YRotSpeed      = 30;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {

        var deltaTime = Time.deltaTime; 

        var newPos = transform.position;


        // handle rotation

        if (Input.GetKey(KeyCode.UpArrow))
            transform.Rotate(new Vector3(-XRotSpeed * deltaTime, 0), Space.Self);
        else if (Input.GetKey(KeyCode.DownArrow))
            transform.Rotate(new Vector3(XRotSpeed * deltaTime, 0), Space.Self);

        if (Input.GetKey(KeyCode.LeftArrow))
            transform.Rotate(new Vector3(0, -XRotSpeed * deltaTime), Space.World);
        else if (Input.GetKey(KeyCode.RightArrow))
            transform.Rotate(new Vector3(0, XRotSpeed * deltaTime), Space.World);


        // handle position

        if (Input.GetKey(KeyCode.W))
            newPos += transform.forward * ForwardSpeed * deltaTime;
        else if (Input.GetKey(KeyCode.S))
            newPos -= transform.forward * BackSpeed * deltaTime;


        if (Input.GetKey(KeyCode.A))
            newPos -= transform.right * SideSpeed * deltaTime;
        else if (Input.GetKey(KeyCode.D))
            newPos += transform.right * SideSpeed * deltaTime;

        if (Input.GetKey(KeyCode.Q))
            newPos += transform.up * UpSpeed * deltaTime;
        else if (Input.GetKey(KeyCode.E))
            newPos -= transform.up * DownSpeed * deltaTime;

        transform.position = newPos;

	
	}
}
