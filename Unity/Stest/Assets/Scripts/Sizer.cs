using UnityEngine;
using System.Collections;

public class AbsoluteSizer : MonoBehaviour
{
    public bool     UseInitialScaleAsTargetScale = false;

    public Vector3  TargetScale = new Vector3( 1.4f, 1.4f, 1.4f);

    public float    ResizeSpeed = 10.0f;


    public bool     IsAlwaysActive = false;
    public KeyCode  TriggerKeyCode = KeyCode.Space;



    private bool    _isActive = false;


	// Use this for initialization
	void Start()
    {
        if (UseInitialScaleAsTargetScale)
        {
            TargetScale = transform.localScale;
        }
	
	}
	
	// Update is called once per frame
	void Update()
    {

        if ( IsAlwaysActive || Input.GetKeyDown(TriggerKeyCode) || _isActive)
        {
            float resizeSpeed = ResizeSpeed * Time.deltaTime;

            float newScaleX = Mathf.MoveTowards(transform.localScale.x, TargetScale.x, resizeSpeed);
            float newScaleY = Mathf.MoveTowards(transform.localScale.y, TargetScale.y, resizeSpeed);
            float newScaleZ = Mathf.MoveTowards(transform.localScale.z, TargetScale.z, resizeSpeed);

            Vector3 newScale = new Vector3(newScaleX, newScaleY, newScaleZ);

            transform.localScale = newScale;

            _isActive = newScaleX == TargetScale.x ? false : true;
        }
	
	}
}
