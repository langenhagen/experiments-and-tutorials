using UnityEngine;
using System.Collections;

public class OnOff : MonoBehaviour {

    public GameObject Item1;
    public KeyCode    Item1Key;

    public GameObject Item2;
    public KeyCode    Item2Key;

    public GameObject Item3;
    public KeyCode    Item3Key;

    public GameObject Item4;
    public KeyCode    Item4Key;
    
    public GameObject Item5;
    public KeyCode    Item5Key;

    public GameObject Item6;
    public KeyCode    Item6Key;

    public GameObject Item7;
    public KeyCode    Item7Key;

    public GameObject Item8;
    public KeyCode    Item8Key;

    public GameObject Item9;
    public KeyCode    Item9Key;

    //##################################################################################################
    // METHODS

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {

        ToggleActive(Item1, Item1Key);
        ToggleActive(Item2, Item2Key);
        ToggleActive(Item3, Item3Key);
        ToggleActive(Item4, Item4Key);
        ToggleActive(Item5, Item5Key);
        ToggleActive(Item6, Item6Key);
        ToggleActive(Item7, Item7Key);
        ToggleActive(Item8, Item8Key);
        ToggleActive(Item9, Item9Key);
	}

    //##################################################################################################
    // HELPERS

    private void ToggleActive(GameObject item, KeyCode keyCode)
    {
        if (Input.GetKeyDown(keyCode) && item != null)
        {
            item.SetActive(!item.activeSelf);
        }
    }
}
