using UnityEngine;
using System.Collections;

using System.Collections.Generic;

public class MandelAnordner : MonoBehaviour {

    public float MandelShape = 0;

    public Transform Prototype;

    public int Num = 10;

    public float Distance = 0.2f;


    private float _oldMandelShape;
    private int _oldNum;
    private float _oldDistance;

    private List<Transform> Mandels;

	// Use this for initialization
	void Start () {

        Mandels = new List<Transform>();

        AnordneMandel();
	}
	
	// Update is called once per frame
	void Update () {

        if (_oldNum != Num || _oldDistance != Distance)
            AnordneMandel();

        if (_oldMandelShape != MandelShape)
            ReshapeMandel();

	}

    void AnordneMandel()
    {
        while (Mandels.Count > 0)
            Destroy(Mandels[0].gameObject);

        _oldDistance = Distance;
        _oldNum = Num;

        for (int i = 0; i < Num; ++i)
        {
            var newObject = Instantiate(Prototype) as Transform;
            newObject.Rotate(new Vector3(1, 0, 0), Mathf.Deg2Rad * 90);
            newObject.position = new Vector3(0, 0, i * Distance);

            Mandels.Add(newObject);
        }

        for (int i = 1; i < Num; ++i)
        {
            var newObject = Instantiate(Prototype) as Transform;
            newObject.Rotate(new Vector3(1, 0, 0), Mathf.Deg2Rad * 90);
            newObject.position = new Vector3(0, 0, - i * Distance);

            Mandels.Add(newObject);
        }
    }

    void ReshapeMandel()
    {
        _oldMandelShape = MandelShape;

        foreach (var m in Mandels)
        {
            if (m.renderer.material.HasProperty("Cimag"))
                m.renderer.material.SetFloat("Cimag", MandelShape);
        }
    }
}
