package sam.rdev.sayurku.activity.login

import sam.rdev.sayurku.model.User

interface LoginView {
    fun onSuccessLogin(user: User?)
    fun onErrorLogin(msg: String?)
}