/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configs;

import Model.Feedback;
import java.util.List;

public class CalculateFeedback {

    public float totalStar(List<Feedback> feedbacks) {
        if (feedbacks.size() == 0) {
            return 0;
        }
        int sumStar = 0;
        int sumCount = 0;
        for (Feedback f : feedbacks) {
            sumStar += f.getStar();
            sumCount++;
        }
        if (sumCount == 0) {
            return 0;
        }
        float starTotal = (float) sumStar / sumCount;
        starTotal = Math.round(starTotal * 100) / 100.0f;
        return starTotal > 5 ? 5 : starTotal;
    }

    public double floor(float total) {
        return Math.floor((float) total);
    }

}



