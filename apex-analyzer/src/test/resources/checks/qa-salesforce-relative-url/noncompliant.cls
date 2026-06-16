public class RelativeUrlNonCompliant {

    private Id recordId;

    public RelativeUrlNonCompliant(Id recId) {
        recordId = recId;
    }

    public String getRelativePath() {
        return '/' + recordId;
    }

    public String getUrl() {
        return 'https://myorg.lightning.force.com/lightning/page';
    }
}
