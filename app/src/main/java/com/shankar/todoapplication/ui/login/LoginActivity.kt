package com.shankar.todoapplication.ui.login

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import com.shankar.todoapplication.R
import com.shankar.todoapplication.base.BaseActivity
import com.shankar.todoapplication.base.ViewModelFactory
import com.shankar.todoapplication.databinding.ActivityLoginBinding
import com.shankar.todoapplication.repository.RoomDataBaseRepository
import com.shankar.todoapplication.ui.TodoActivity
import com.shankar.todoapplication.ui.login.LoginViewModel
import com.shankar.todoapplication.utils.afterTextChanged

class LoginActivity : BaseActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(
            this,
            ViewModelFactory(RoomDataBaseRepository(database.categoryDao()))
        )[LoginViewModel::class.java]

        loginViewModel.loginFormState.observe(this@LoginActivity) {
            val loginState = it ?: return@observe

            // disable login button unless both username / password is valid
            binding.login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                binding.username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                binding.password.error = getString(loginState.passwordError)
            }
        }

        loginViewModel.loginResult.observe(this@LoginActivity) {
            val loginResult = it ?: return@observe

            binding.loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        }

        binding.username.afterTextChanged {
            loginViewModel.loginDataChanged(
                binding.username.text.toString(),
                binding.password.text.toString()
            )
        }

        binding.password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    binding.username.text.toString(),
                    binding.password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE -> doLogin()
                }
                false
            }

            binding.login.setOnClickListener {
                doLogin()
            }
        }
    }

    private fun doLogin() {
        binding.loading.visibility = View.VISIBLE
        loginViewModel.login(
            binding.username.text.toString(),
            binding.password.text.toString()
        )
    }

    override fun layout(): View {
        return binding.root
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
        startActivity(TodoActivity.getIntent(this))
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

