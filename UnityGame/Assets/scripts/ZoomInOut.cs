using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ZoomInOut : MonoBehaviour
{
   
    public float zoomSpeed; 
    private Camera mainCamera;
    public int endPointfieldview=120;
    
 
    void Start()
    {
        mainCamera = GetComponent<Camera>();
        mainCamera.fieldOfView=140;
    }
    
    void Update()
    {
        
        Zoom();
        
        
    }
 
    private void Zoom()
    {
        
        if((mainCamera.fieldOfView > endPointfieldview)&&(Ui_clicked_destroy.clickSetter==true))
        {
            mainCamera.fieldOfView -= zoomSpeed;
            if(mainCamera.fieldOfView<=endPointfieldview){
                Ui_clicked_destroy.clickSetter=false;
            }
        }
    }
 
    
}
