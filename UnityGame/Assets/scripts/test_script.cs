using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class test_script : MonoBehaviour
{
    public TextMesh textmesh;
    private bool isTextHidden=true;
    public int IncreaseRateOfHeart=100;
    public float timer;
    public int waitingTime;
    private bool canClick = true;

    void Start()
    {
        timer =0;
        textmesh.gameObject.SetActive(false);
    }
    void Update()
    {   //일정한 시간이 되어야지 하트를 얻을 수 있도록 설정한다.
    //문제점은 클릭을 함과 동시에 다른 하트들도 같이 클릭이 되어서 각각 따로 따로 하트를 수급할 수 있는 시스템을 마련하는 게 좋을 것 같다.
        timer+=Time.deltaTime;
        if(timer>waitingTime){
            ShowText();
            if(canClick&&Input.GetMouseButtonDown(0))
            {
                StartCoroutine(GiveHearts());
         
            }
        
        }
        
    }

    IEnumerator GiveHearts()
    {
        canClick=false;

        if((HeartStatus.currentHeart <= HeartStatus.maxHeart))
        {
            HeartStatus.currentHeart += IncreaseRateOfHeart;
            if(HeartStatus.currentHeart >= HeartStatus.maxHeart)
            {
                HeartStatus.currentHeart = HeartStatus.maxHeart;
            }
        }
        HideText();
        timer=0;
         yield return new WaitForSeconds(1f); // 클릭 간격을 원하는 시간으로 조절
         canClick=true;
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
