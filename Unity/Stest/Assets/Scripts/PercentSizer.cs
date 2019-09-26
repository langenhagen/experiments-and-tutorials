using UnityEngine;
using System.Collections;

public class PercentSizer : MonoBehaviour
{
    ///////////////////////////////////////////////////////////////////////////////////////////////
    // PUBLIC VARS

    public string  Note = "";

    public KeyCode TriggerKeyCode = KeyCode.Space;
    
    public bool    CalcTargetScaleOnTrigger = false;

    public Vector3 TargetScalePercent = new Vector3(1.4f, 1.4f, 1.4f);

    public float ResizeSpeed = 10.0f;


    

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // PRIVATE VARS

    private bool    _isActive = false;
    private Vector3 _oldTargetScalePercent;
    private Vector3 _targetScale;


    ///////////////////////////////////////////////////////////////////////////////////////////////
    // METHODS

    // Use this for initialization
    void Start()
    {
        _targetScale = Vector3.Scale(transform.localScale, TargetScalePercent);

        _oldTargetScalePercent = TargetScalePercent;
    }

    // Update is called once per frame
    void Update()
    {
        Resize();     
    }

    public void Resize()
    {
        if (_oldTargetScalePercent != TargetScalePercent ||
            (Input.GetKeyDown(TriggerKeyCode) && CalcTargetScaleOnTrigger))
        {
            // if target scales changed or it the target scale shall be based on _current_ size
            this.Start();
        }


        if (Input.GetKeyDown(TriggerKeyCode) || TriggerKeyCode == 0 || _isActive)
        {

            float resizeSpeed = ResizeSpeed * Time.deltaTime;

            float newScaleX = Mathf.MoveTowards(transform.localScale.x, _targetScale.x, resizeSpeed);
            float newScaleY = Mathf.MoveTowards(transform.localScale.y, _targetScale.y, resizeSpeed);
            float newScaleZ = Mathf.MoveTowards(transform.localScale.z, _targetScale.z, resizeSpeed);

            Vector3 newScale = new Vector3(newScaleX, newScaleY, newScaleZ);

            transform.localScale = newScale;

            _isActive = newScaleX == _targetScale.x ? false : true;
        }
    }
}
