using UnityEngine;
using System.Collections;

public class ShaderPropertyChangerGroup : MonoBehaviour {
	public GameObject targetObjectGroup;
	Texture2D Image;
	public string PropertyName;
	WWW www;
	public string path;
	//public GameObject[] objects = new GameObject[numberOfObjects];

	// Use this for initialization
	IEnumerator  Start (){

		path = "file:///"+path;
		www = new WWW (path);	
		yield return www;
		Image = www.texture;
		replaceLightMap(targetObjectGroup,Image,PropertyName);

	}

	void replaceLightMap (GameObject obj, Texture2D tex,string property)
		{
			for (int i  = 0; i < obj.transform.childCount; i++) {
				Transform tObjects = obj.transform.GetChild (i);
				GameObject oObject = tObjects.gameObject;
				print ("Changing "+property+" Property of Game Object " + oObject);
				oObject.renderer.material.SetTexture (property, tex);
				
			}
		}

	// Update is called once per frame
	void Update () {
	
	}
	void OnDrawGizmos(){
		print ("Please make sure the following fields are correct:\n");
		print ("1) Object assigned in the Inspector\n");
		print ("2) Please check the path of the Texture assigned\n");
		print ("3) Property name must be as it is in the shader");
		print ("Please read documentation for more detailed explanation");
	}
}
