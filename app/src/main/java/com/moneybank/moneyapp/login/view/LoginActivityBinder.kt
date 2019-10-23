package com.moneybank.moneyapp.login.view

import android.animation.Animator
import com.airbnb.lottie.LottieDrawable
import com.example.minimoneybox.login.model.LoginRequestDto
import com.moneybank.moneyapp.R
import com.moneybank.moneyapp.base.AbstractBinding
import com.moneybank.moneyapp.databinding.ActivityLoginBinding
import java.util.regex.Pattern

/**
 * [LoginActivityBinder] :
 *
 * @author : Nirav Joshi
 * @version 1.0.0
 * @since 10/14/2019
 */
class LoginActivityBinder : AbstractBinding<ActivityLoginBinding>() {
    companion object {
        val EMAIL_REGEX = "[^@]+@[^.]+\\..+"
        val NAME_REGEX = "[a-zA-Z]{6,30}"
        val PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[A-Z]).{10,50}$"
        val firstAnim = 0 to 109
        val secondAnim = 131 to 158
    }

    override fun onCreated() {
        binding?.loginBinder = this
        setUpAnimation()
    }

    private fun setUpAnimation() {
        binding?.animation?.playAnimation()
        binding?.animation?.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                // Left out because "Unused"
            }

            override fun onAnimationEnd(animation: Animator?) {
                if (binding?.animation?.isAnimating == false) {
                    binding?.animation?.repeatCount = LottieDrawable.INFINITE
                    binding?.animation?.setMinAndMaxFrame(secondAnim.first, secondAnim.second)
                    binding?.animation?.speed = 0.7f
                    binding?.animation?.playAnimation()
                }
            }

            override fun onAnimationCancel(animation: Animator?) {
                // Left out because "Unused"
            }

            override fun onAnimationStart(animation: Animator?) {
                // Left out because "Unused"
            }
        })
    }

    override fun onDestroy() {
        binding?.loginBinder = null
    }

    fun isInputValid(): Boolean {
        var isvalid = false
        binding?.tilEmail?.let { til ->
            til.editText?.text?.toString()?.let {
                if (it.isNotEmpty() && Pattern.matches(EMAIL_REGEX, it)) {
                    isvalid = true
                    til.error = null
                } else {
                    isvalid = false
                    til.error = til.context?.getString(R.string.email_address_error)
                }
            }
        }
        binding?.tilPassword?.let { til ->
            til.editText?.text?.toString()?.let {
                if (it.isNotEmpty() && Pattern.matches(PASSWORD_REGEX, it)) {
                    isvalid = true
                    til.error = null
                } else {
                    isvalid = false
                    til.error = til.context?.getString(R.string.password_error)
                }
            }
        }
        binding?.tilName?.let { til ->
            til.editText?.text?.toString()?.let {
                if (it.isEmpty()) {
                    isvalid = true
                    til.error = null
                } else if (it.isNotEmpty() && Pattern.matches(NAME_REGEX, it)) {
                    isvalid = true
                    til.error = null
                } else {
                    isvalid = false
                    til.error = til.context?.getString(R.string.full_name_error)
                }
            }
        }
        return isvalid
    }

    fun getRequestObject(): LoginRequestDto {
        return LoginRequestDto().apply {
            this.email = binding?.tilEmail?.editText?.text?.toString()
            this.password = binding?.tilPassword?.editText?.text?.toString()
        }
    }
}