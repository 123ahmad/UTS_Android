package sam.rdev.sayurku.activity.login.data

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName
import sam.rdev.sayurku.model.User

@Generated("com.robohorse.robopojogenerator")
data class ResultLogin(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("status")
	val status: Int? = null
)