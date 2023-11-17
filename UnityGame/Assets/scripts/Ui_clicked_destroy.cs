using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;

public class Ui_clicked_destroy : MonoBehaviour, IPointerDownHandler
{
    public static bool clickSetter=false;
    public GameObject ForDestroy;
    
    //마우스를 클릭하면 image와 image에 있는 textmeshpro가 없어진다
    public void OnPointerDown(PointerEventData data)
    {
        Destroy(ForDestroy);
        clickSetter=true;
                       
        
    }

    
}
