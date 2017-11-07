/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capitanesdegondor.rolemastermanager.service.beans;

import java.util.List;

/**
 *
 * @author TiranoJuan
 */
public class CampaignListDTO {

    List<CampaignDTO> campaigns;

    public CampaignListDTO() {
    }

    public List<CampaignDTO> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<CampaignDTO> campaigns) {
        this.campaigns = campaigns;
    }

    
    
}
