using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ClickMusicButton : MonoBehaviour
{
    public Button toggleButton;//버튼 오브젝트를 할당할 public 변수
    public AudioSource musicSource;
    //음악을 재생하는 AudioSource컴포넌트
    private bool isMusicOn = true;//음악이 켜져있는지 안켜져있는지

    void Start()
    {
        toggleButton.onClick.AddListener(ToggleMusicState);
    }

   void ToggleMusicState()
   {
        isMusicOn=!isMusicOn;

        if(isMusicOn){
            musicSource.Play();
        }
        else{
            musicSource.Pause();
        }

        UpdateButtonText();
   }

   void UpdateButtonText(){
    //버튼의 텍스트를 음악의 상태에 따라 변경합니다.
    toggleButton.GetComponentInChildren<Text>().text=isMusicOn?"Music Off":"Music On";
   }
}
