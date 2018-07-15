package Models;

/**
 * Created by eva on 13/07/18.
 */

public class Project {

    String searchTerm;
    String searchURl;
    String totalproposals;
    String proposalId;
    String proposalTitle;
    String proposalDescription;
    String proposalUrl;
    String projectFunds;
    String projectExpiration;
    String num_of_donors;

    public String getNum_of_donors() {
        return num_of_donors;
    }

    public void setNum_of_donors(String num_of_donors) {
        this.num_of_donors = num_of_donors;
    }

    public String getProjectFunds() {
        return projectFunds;
    }

    public void setProjectFunds(String projectFunds) {
        this.projectFunds = projectFunds;
    }

    public String getProjectExpiration() {
        return projectExpiration;
    }

    public void setProjectExpiration(String projectExpiration) {
        this.projectExpiration = projectExpiration;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getSearchURl() {
        return searchURl;
    }

    public void setSearchURl(String searchURl) {
        this.searchURl = searchURl;
    }

    public String getTotalproposals() {
        return totalproposals;
    }

    public void setTotalproposals(String totalproposals) {
        this.totalproposals = totalproposals;
    }

    public String getProposalId() {
        return proposalId;
    }

    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }

    public String getProposalTitle() {
        return proposalTitle;
    }

    public void setProposalTitle(String proposalTitle) {
        this.proposalTitle = proposalTitle;
    }

    public String getProposalDescription() {
        return proposalDescription;
    }

    public void setProposalDescription(String proposalDescription) {
        this.proposalDescription = proposalDescription;
    }

    public String getProposalUrl() {
        return proposalUrl;
    }

    public void setProposalUrl(String proposalUrl) {
        this.proposalUrl = proposalUrl;
    }
}
