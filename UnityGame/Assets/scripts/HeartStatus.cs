using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class HeartStatus : MonoBehaviour
{
    public static int maxHeart = 10000;
    public static int currentHeart=0;
    public Slider heartSlider;

    void Start()
    {//스크립트가 실행될때 한번 실행된다.
        
       
    }

    void Update(){//매 프레임마다 실행된다.
        heartSlider.value = currentHeart;//slider의 값을 조정한다.
        if (currentHeart < 0)
        {
            Debug.Log("heart is under 0");//currentHeart의 수가 0이하로 떨어지는 일은 없다.
        }
    }
         
}
