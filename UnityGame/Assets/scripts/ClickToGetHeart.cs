using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ClickToGetHeart : MonoBehaviour
{
    public TextMesh textmesh;
    private bool isTextHidden=true;
    public int IncreaseRateOfHeart=100;
    public float timer;
    public int waitingTime;

    void Start()
    {
        timer =0;
        waitingTime=2;
        textmesh.gameObject.SetActive(false);
    }
    void Update()
    {   //일정한 시간이 되어야지 하트를 얻을 수 있도록 설정한다.
        timer+=Time.deltaTime;
        if(timer>waitingTime){
            ShowText();
            if(Input.GetMouseButtonDown(0))
            {
            Ray ray=Camera.main.ScreenPointToRay(Input.mousePosition);
            RaycastHit hit;
            if(Physics.Raycast(ray,out hit)&&(HeartStatus.currentHeart<=HeartStatus.maxHeart))
            {
                
                HeartStatus.currentHeart+=IncreaseRateOfHeart;
                if(HeartStatus.currentHeart>=HeartStatus.maxHeart){
                    HeartStatus.currentHeart=HeartStatus.maxHeart;
                }
            }
            HideText();
            timer=0;
            }
        
        }
        
    }

    private void ShowText()
    {
        textmesh.gameObject.SetActive(true);
        isTextHidden=false;
    }

     private void HideText()
    {
        textmesh.gameObject.SetActive(false);
        isTextHidden=true;
    }
}
