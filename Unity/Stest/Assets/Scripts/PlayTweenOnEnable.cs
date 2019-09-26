using UnityEngine;
using System.Collections;

public class PlayTweenOnEnable : MonoBehaviour {

    void OnEnable()
    {
        var tweener = GetComponent<UITweener>();

        if (tweener != null)
        {
            tweener.ResetToBeginning();
            tweener.PlayForward();
        }
        else
        {
            print("ERROR: " + gameObject + ": tweener is null!");
        }
    }
}
