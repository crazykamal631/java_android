package web.com.testbinding3;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class User extends ViewModel {
    public User() {
        loadData();
    }

    private MutableLiveData<String> name = new MutableLiveData<>();
    private MutableLiveData<String> email = new MutableLiveData<>();

    public MutableLiveData<String> getName() {
        return name;
    }

    public void setName(MutableLiveData<String> name) {
        this.name = name;
    }

    public MutableLiveData<String> getEmail() {
        return email;
    }

    public void setEmail(MutableLiveData<String> email) {
        this.email = email;
    }

    private void loadData() {
        name.setValue("xxxx");
        email.setValue("xxx.xx@xx.com");
    }
}
