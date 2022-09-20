package QuanLiKhoaHoc.DTO;

public class Account {
    int AccountId, PersonId;
    String AccountName, AccountPassword;

    public Account(int accountId, int personId, String accountName, String accountPassword) {
        AccountId = accountId;
        PersonId = personId;
        AccountName = accountName;
        AccountPassword = accountPassword;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
    }

    public int getPersonId() {
        return PersonId;
    }

    public void setPersonId(int personId) {
        PersonId = personId;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public String getAccountPassword() {
        return AccountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        AccountPassword = accountPassword;
    }

    @Override
    public String toString() {
        return "Account{" +
                "AccountId=" + AccountId +
                ", PersonId=" + PersonId +
                ", AccountName='" + AccountName + '\'' +
                ", AccountPassword='" + AccountPassword + '\'' +
                '}';
    }
}
