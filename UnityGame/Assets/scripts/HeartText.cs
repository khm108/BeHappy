using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro;

public class HeartText : MonoBehaviour
{
    public TextMeshProUGUI numOfHeart;
    private string information="Heart: ";
    // Start is called before the first frame update
    void Start()
    {
        numOfHeart=GetComponent<TextMeshProUGUI>();
    }

    // Update is called once per frame
    void Update()
    {
        numOfHeart.text=information+HeartStatus.currentHeart.ToString();
    }
}
